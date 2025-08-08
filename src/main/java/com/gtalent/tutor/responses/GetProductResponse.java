package com.gtalent.tutor.responses;

import com.gtalent.tutor.models.Product;
import com.gtalent.tutor.models.Supplier;
import com.gtalent.tutor.models.User;
import jakarta.persistence.Column;

import java.util.Optional;

public class GetProductResponse {
    private String name;
    private  int id;
    private  int price;
    private  int quantity;
    private  int status;
    private  Integer supplier_id;


    public GetProductResponse(){
    }

    public GetProductResponse(String name,int id, int price, int quantity, int status, Integer supplier_id) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.supplier_id = supplier_id;
    }
    public GetProductResponse(Product product){
        this.name = product.getName();
        this.id =product.getId();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.status = product.getStatus();
        this.supplier_id = product.getSupplier_id();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
