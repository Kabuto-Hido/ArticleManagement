package com.example.demo.service;

import com.example.demo.dto.payment.TransactionDTO;
import com.example.demo.dto.payment.VNPaymentRequestDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public interface PaymentService {
    public Map<String, String> returnParamVnPay(VNPaymentRequestDTO paymentRequestDTO);

    public String returnPaymentUrl(Map<String, String> vnp_Params) throws UnsupportedEncodingException;

    public TransactionDTO resultTransaction(HttpServletRequest request);
}
