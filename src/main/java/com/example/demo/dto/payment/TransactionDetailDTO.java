package com.example.demo.dto.payment;

import com.example.demo.dto.order.OrderDTO;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDetailDTO {
    private OrderDTO order;
    private String amount;
    //private String bankCode;
    private String description;
    //private String cardType;
    private String payDate;

}
