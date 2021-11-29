package com.lysenko.Payments.model.entity.payment;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Column(name = "date")
    private Date dateCreated;
    @Column(name = "account_id")
    private int accountId;

}
