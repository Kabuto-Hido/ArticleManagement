package com.example.demo.service.impl;

import com.example.demo.config.EmailTemplate;
import com.example.demo.config.GlobalVariable;
import com.example.demo.config.PayPalConfig;
import com.example.demo.dto.order.OrderDTO;
import com.example.demo.dto.payment.TransactionDTO;
import com.example.demo.dto.payment.TransactionDetailDTO;
import com.example.demo.dto.user.ProfileDTO;
import com.example.demo.service.EmailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PayPalService;
import com.example.demo.service.UserService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PayPalServiceImpl implements PayPalService {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    private APIContext context = new APIContext(PayPalConfig.CLIENTID,
            PayPalConfig.CLIENTSECRET, PayPalConfig.MODE);

    @Override
    public Payment createPayment(long orderId, String total) throws PayPalRESTException {
        System.out.println("Start paypal payment!!");
        Amount amount = new Amount();
        amount.setCurrency(PayPalConfig.CURRENCY);
        amount.setTotal(total);

        Transaction transaction = new Transaction();
        transaction.setDescription("Payment Order " + orderId);
        transaction.setAmount(amount);
        transaction.setInvoiceNumber(String.valueOf(orderId));

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(PayPalConfig.paypalPaymentMethod.PAYPAL.name());

        Payment payment = new Payment();
        payment.setIntent(PayPalConfig.paypalPaymentIntent.SALE.name());
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(PayPalConfig.CANCEL_URL);
        redirectUrls.setReturnUrl(PayPalConfig.SUCCESS_URL);
        payment.setRedirectUrls(redirectUrls);

        orderService.changeStatusOrder(orderId,
                GlobalVariable.ORDER_STATUS.PAYMENT_CONFIRM.name());

        return payment.create(context);
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(context, paymentExecute);
    }

    @Override
    public TransactionDTO fetchTransactionData(Payment payment) {
        TransactionDTO transactionDTO = new TransactionDTO();
        List<Transaction> transactions = payment.getTransactions();
        long orderId = Long.parseLong(transactions.get(0).getInvoiceNumber());

        if (payment.getState().equals("approved")) {
            transactionDTO.setStatus("OK");
            transactionDTO.setMessage("Successfully");

            OrderDTO successOrder = orderService.changeStatusOrder(orderId,
                    GlobalVariable.ORDER_STATUS.DONE.name());

            ProfileDTO newProfile = userService.changeTypeAccount(successOrder.getPayer().getUsername(),
                    "Vip");

            successOrder.setPayer(newProfile);

            TransactionDetailDTO detail = new TransactionDetailDTO();
            detail.setAmount(transactions.get(0).getAmount().getTotal() + transactions.get(0).getAmount().getCurrency());
            detail.setDescription(transactions.get(0).getDescription());
            detail.setPayDate(payment.getCreateTime());
            detail.setOrder(successOrder);

            transactionDTO.setDetail(detail);

            try {
                emailService.sendAsHTML(newProfile.getEmail(),
                        "Thank you for signing up for our VIP membership",
                        EmailTemplate.TemplateInvoice(
                                orderId, newProfile.getUsername(),
                                newProfile.getEmail(), GlobalVariable.PAYMENT_TYPE.PAYPAL.name(),
                                successOrder.getCreatedDate().toString(),
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
