package com.example.demo.dto.orderDetail;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDTO {
    private Long id;
    private Long unitPrice;
    private Long quantity;
    private String description;
}
