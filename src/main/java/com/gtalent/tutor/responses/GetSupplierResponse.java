package com.gtalent.tutor.responses;

import com.gtalent.tutor.models.Supplier;

import java.util.List;

public class GetSupplierResponse {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private List<ProductResponse> products;

    public GetSupplierResponse(Supplier supplier) {
        this.id =supplier.getId();
        this.name = supplier.getName();
        this.phone = supplier.getPhone();
        this.address = supplier.getAddress();
        this.email = supplier.getEmail();
    }

    public GetSupplierResponse(int id, String name, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public GetSupplierResponse(List<ProductResponse> products) {
        this.products = products;
    }


}

