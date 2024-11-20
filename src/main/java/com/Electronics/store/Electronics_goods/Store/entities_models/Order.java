package com.Electronics.store.Electronics_goods.Store.entities_models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class Order {


    @Id
    private String orderId;

    //PENDING,DISPATCHED,DELIVERED,
    //enum
    private String orderStatus;

    //NOT-PAID, PAID
    //enum
    //boolean- false=>NOTPAID  || true=>PAID
    private String paymentStatus;

    private int orderAmount;

    @Column(length = 1000)
    private String billingAddress;

    private String billingPhone;

    private String billingName;

    private Date orderedDate;

    private Date deliveredDate;

    private  String razoryPayOrderId;
    private String paymentId;

    //user
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

}
