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
@Table(name = "orders")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String payment;
    @Column(name = "totalprice")
    private Long totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "id")
    private User userId;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

    public Order(String status, String payment, Long totalPrice, User userId) {
        this.status = status;
        this.payment = payment;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }
}
