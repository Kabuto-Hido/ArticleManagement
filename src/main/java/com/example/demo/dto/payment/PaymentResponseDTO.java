package com.example.demo.dto.payment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentResponseDTO {
    private String status;
    private String message;
    private String url;
}
