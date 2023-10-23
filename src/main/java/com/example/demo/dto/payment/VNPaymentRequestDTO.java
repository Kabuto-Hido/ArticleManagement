package com.example.demo.dto.payment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VNPaymentRequestDTO {
    private long orderId;
    private int amount;
    private String bankCode;
}
