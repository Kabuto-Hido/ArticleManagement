package com.example.demo.dto.order;

import com.example.demo.dto.orderDetail.OrderDetailDTO;
import com.example.demo.dto.user.ProfileDTO;
import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.OrderDetail;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO extends BaseEntity {
    private Long id;
    private String status;
    private String payment;
    private Long totalPrice;
    private ProfileDTO payer;
    private List<OrderDetailDTO> orderDetails;
}
