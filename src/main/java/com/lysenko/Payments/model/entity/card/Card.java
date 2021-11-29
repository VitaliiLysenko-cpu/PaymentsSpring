package com.lysenko.Payments.model.entity.card;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "payment_card")
@Data
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "card_number")
    private String number;
    private Date expiration;
    @Column(name = "cvc_code")
    private int cvc;
    private int accountId;
}