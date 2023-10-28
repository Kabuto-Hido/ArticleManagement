package com.example.demo.controller;

import com.example.demo.Util.ApplicationUserService;
import com.example.demo.config.GlobalVariable;
import com.example.demo.config.VNPayConfig;
import com.example.demo.dto.payment.PaymentResponseDTO;
import com.example.demo.dto.payment.VNPaymentRequestDTO;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequestMapping("/vnpay")
public class VNPayController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-payment")
    public ResponseEntity<?> createPaymentVnPay(@RequestBody VNPaymentRequestDTO paymentRequestDTO) throws UnsupportedEncodingException {
        String username = ApplicationUserService.GetUsernameLoggedIn();
        Order order = orderService.orderCreator(username,
                GlobalVariable.PAYMENT_TYPE.VN_PAY.name(), String.valueOf(GlobalVariable.VN_Price));
        paymentRequestDTO.setOrderId(order.getId());

        Map<String, String> vnp_Params = paymentService.returnParamVnPay(paymentRequestDTO);
        String paymentUrl = paymentService.returnPaymentUrl(vnp_Params);
        PaymentResponseDTO paymentResultDTO = new PaymentResponseDTO("OK", "Success", paymentUrl);
        return ResponseEntity.status(HttpStatus.OK).body(paymentResultDTO);
    }

    @GetMapping("/payment-detail")
    public ResponseEntity<?> transaction(HttpServletRequest request) {
        return ResponseEntity.ok(paymentService.resultTransaction(request));
    }
}
