package com.gtalent.tutor.models;

import jakarta.persistence.*;
@Entity
@Table(name = "products")
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

    @Column(name = "name")
    private String  name;

    @Column(name = "price")
    private  int price;

    @Column(name = "quantity")
    private  int quantity;

    @Column(name = "status")
    private  int status;

    @Column(name = "supplier_id")
    private  Integer supplier_id;

    public Product(){

    }

    public Product(int id, String name, int price, int quantity, int status, Integer supplier_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.supplier_id = supplier_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(Integer supplier_id) {
        this.supplier_id = supplier_id;
    }
}
