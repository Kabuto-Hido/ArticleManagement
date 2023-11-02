package com.example.demo.service.impl;

import com.example.demo.config.EmailTemplate;
import com.example.demo.config.GlobalVariable;
import com.example.demo.config.VNPayConfig;
import com.example.demo.dto.order.OrderDTO;
import com.example.demo.dto.payment.TransactionDTO;
import com.example.demo.dto.payment.TransactionDetailDTO;
import com.example.demo.dto.payment.VNPaymentRequestDTO;
import com.example.demo.dto.user.ProfileDTO;
import com.example.demo.service.EmailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @Override
    public Map<String, String> returnParamVnPay(VNPaymentRequestDTO paymentRequestDTO) {
        int amount = GlobalVariable.VN_Price * 100;
        String orderID = String.valueOf(paymentRequestDTO.getOrderId());//VNPayConfig.getRandomNumber(8);

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNPayConfig.VERSIONVNPAY);
        vnp_Params.put("vnp_Command", VNPayConfig.COMMAND_PAY);
        vnp_Params.put("vnp_TmnCode", VNPayConfig.TMNCODE);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", VNPayConfig.CURRCODE);
        vnp_Params.put("vnp_BankCode", paymentRequestDTO.getBankCode());
        vnp_Params.put("vnp_TxnRef", orderID);

        vnp_Params.put("vnp_OrderInfo", "Payment Order " + orderID);
        vnp_Params.put("vnp_OrderType", VNPayConfig.ORDERTYPE);
        vnp_Params.put("vnp_Locale", VNPayConfig.LOCALEDEFAULT);
        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.RETURNURL);
        vnp_Params.put("vnp_IpAddr", VNPayConfig.IPDEFAULT);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = format.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = format.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        return vnp_Params;
    }

    @Override
    public String returnPaymentUrl(Map<String, String> vnp_Params) throws UnsupportedEncodingException {
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.CHECKSUM, hashData.toString());
        String queryUrl = query + "&vnp_SecureHash=" + vnp_SecureHash;

        return VNPayConfig.VNPAYURL + "?" + queryUrl;
    }

    @Override
    public TransactionDTO resultTransaction(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter("vnp_TxnRef"));
        String transactionStatus = request.getParameter("vnp_TransactionStatus");
        TransactionDTO transactionDTO = new TransactionDTO();
        if (transactionStatus.equals("00")) {
            transactionDTO.setStatus("OK");
            transactionDTO.setMessage("Successfully");

            OrderDTO successOrder = orderService.changeStatusOrder(orderId,
                    GlobalVariable.ORDER_STATUS.PAYMENT_CONFIRM.name());

            ProfileDTO newProfile = userService.changeTypeAccount(successOrder.getPayer().getUsername(),
                    "Vip");

            successOrder.setPayer(newProfile);

            TransactionDetailDTO detail = new TransactionDetailDTO();
            int returnAmount = Integer.parseInt(request.getParameter("vnp_Amount")) / 100;
            detail.setAmount(returnAmount + " " + VNPayConfig.CURRCODE);
            //detail.setBankCode(request.getParameter("vnp_BankCode"));
            detail.setDescription(request.getParameter("vnp_OrderInfo"));
            detail.setPayDate(request.getParameter("vnp_PayDate"));
            detail.setOrder(successOrder);
            //detail.setCardType(request.getParameter("vnp_CardType"));

            transactionDTO.setDetail(detail);

            try {
                emailService.sendAsHTML(newProfile.getEmail(),
                        "Thank you for signing up for our VIP membership",
                        EmailTemplate.TemplateInvoice(
                                orderId, newProfile.getUsername(),
                                newProfile.getEmail(), GlobalVariable.PAYMENT_TYPE.VN_PAY.name(),
                                GlobalVariable.convertTimeStampToDate(request.getParameter("vnp_PayDate")),
                                "VIP MemberShip", detail.getAmount()));
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }


        } else {

            OrderDTO failOrder = orderService.changeStatusOrder(orderId,
                    GlobalVariable.ORDER_STATUS.CANCELED.name());
            transactionDTO.setStatus("NO");
            transactionDTO.setMessage("Failed");
        }
        return transactionDTO;
    }
}
