package com.example.demo.service;

import com.example.demo.dto.payment.TransactionDTO;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

@Service
public interface PayPalService {
    Payment createPayment(long orderId, String total) throws PayPalRESTException;
    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
    TransactionDTO fetchTransactionData(Payment payment);
}
