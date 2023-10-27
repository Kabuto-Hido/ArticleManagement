package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "orderdetail")
public class OrderDetail extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="unitprice")
    private Long unitPrice;
    private Long quantity;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "orderId", nullable = false, referencedColumnName = "id")
    private Order orderId;

    public OrderDetail(Long unitPrice, Long quantity, String description, Order orderId) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.description = description;
        this.orderId = orderId;
    }
}
