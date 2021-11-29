package com.lysenko.Payments.model.entity.account;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String number;
    private double balance;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "user_id")
    private int userId;
}
