package com.example.library.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PURCHASE_ORDER")
@Data
public class PurchaseOrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
