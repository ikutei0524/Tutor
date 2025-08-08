package com.gtalent.tutor.models.request;

import com.gtalent.tutor.models.Product;

public class CreateProductRequest {
    private String name;

    private  int price;
    private int quantity;
    private int status;
    private Integer supplier_id;

    public CreateProductRequest(){

    }

    public CreateProductRequest(Product savedProduct) {
    }

    public CreateProductRequest(String name, int price, int quantity, int status, Integer supplier_id) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.supplier_id = supplier_id;
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
