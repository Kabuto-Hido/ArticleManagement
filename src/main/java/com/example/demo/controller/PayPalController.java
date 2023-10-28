package com.example.demo.controller;

import com.example.demo.Util.ApplicationUserService;
import com.example.demo.config.GlobalVariable;
import com.example.demo.dto.order.OrderDTO;
import com.example.demo.dto.payment.PaymentResponseDTO;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import com.example.demo.service.PayPalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paypal")
public class PayPalController {
    @Autowired
    private PayPalService payPalService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-payment")
    public ResponseEntity<?> createPaymentPalPay() {
        String username = ApplicationUserService.GetUsernameLoggedIn();
        String usdPrice = GlobalVariable.convertToUSD(GlobalVariable.VN_Price);
        Order order = orderService.orderCreator(username,
                GlobalVariable.PAYMENT_TYPE.PAYPAL.name(),
                String.valueOf(GlobalVariable.VN_Price));
        String returnLink = "";
        try {
            Payment payment = payPalService.createPayment(order.getId(), usdPrice);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    returnLink = link.getHref();
                    break;
                }
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        PaymentResponseDTO paymentResultDTO =
                new PaymentResponseDTO("OK", "Success", returnLink);
        return ResponseEntity.status(HttpStatus.OK).body(paymentResultDTO);
    }

    @GetMapping("/URL_PAYPAL_CANCEL")
    public String cancelPay() {
        return "cancel";
    }

    @SneakyThrows
    @GetMapping("/URL_PAYPAL_SUCCESS")
    public ResponseEntity<?> successPay(@RequestParam("paymentId") String paymentId,
                             @RequestParam("PayerID") String payerId) {
        Payment payment = payPalService.executePayment(paymentId, payerId);
        return ResponseEntity.ok(payPalService.fetchTransactionData(payment));
    }
}
