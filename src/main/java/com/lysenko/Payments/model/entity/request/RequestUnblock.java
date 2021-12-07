package com.lysenko.Payments.model.entity.request;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "request_unblock")
@Data
public class RequestUnblock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusRequest statusRequest;
    private int accountId;
}
