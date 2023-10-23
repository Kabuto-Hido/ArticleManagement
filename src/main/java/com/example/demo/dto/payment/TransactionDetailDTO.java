package com.example.demo.dto.payment;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDetailDTO {
    private String orderId;
    private String amount;
    private String bankCode;
    private String description;
    private String cardType;
    private String payDate;

}
