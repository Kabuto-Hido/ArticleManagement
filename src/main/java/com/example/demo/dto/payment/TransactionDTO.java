package com.example.demo.dto.payment;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionDTO {
    private String status;
    private String message;
    private TransactionDetailDTO detail;

    public TransactionDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
