package com.electronic.store.dto;

import com.electronic.store.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String orderId;

    private String orderStatus;

    private String paymentStatus;

    private int orderAmount;


    private String billingAddress;

    private String billingPhone;

    private String billingName;

    private Date orderedDate;

    private Date deliveredDate;

    private  String razoryPayOrderId;
    private String paymentId;
    private User user;
}
