package com.gtalent.tutor.responses;

import com.gtalent.tutor.responses.GetSupplierResponse;
import com.gtalent.tutor.models.Product;


import java.math.BigDecimal;

public class ProductResponse {
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private boolean status;
    private GetSupplierResponse supplier;




    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.status = product.isStatus();
        //if (product.getSupplier() != null) {
            //.supplier = new GetSupplierResponse(product.getSupplier());
        //}
    }


    public ProductResponse(int id, String name, BigDecimal price, int quantity, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }



    public ProductResponse() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public GetSupplierResponse getSupplier() {
        return supplier;
    }

    public void setSupplier(GetSupplierResponse supplier) {
        this.supplier = supplier;
    }
}
