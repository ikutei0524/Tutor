package com.gtalent.tutor.Controller;

import com.gtalent.tutor.models.Product;
import com.gtalent.tutor.models.Supplier;
import com.gtalent.tutor.models.User;
import com.gtalent.tutor.models.request.CreateSupplierRequest;
import com.gtalent.tutor.models.request.UpdateSupplierRequest;
import com.gtalent.tutor.repositories.ProductRepository;
import com.gtalent.tutor.repositories.SupplierRepository;
import com.gtalent.tutor.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin("*")

public class SupplierController {
    private final SupplierRepository supplierRepository;
    @Autowired//注入要記得這行
    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;//將suppRE注入
    }

    //以下是CRUD
    @GetMapping
    public ResponseEntity<List<GetSupplierResponse>> getAllSuppliers(){
        List<Supplier>suppliers = supplierRepository.findAll();
        return ResponseEntity.ok(suppliers.stream().map(supplier -> {
                GetSupplierResponse response = new GetSupplierResponse(supplier);
                response.setProducts(supplier.getProducts().stream().map(ProductResponse::new).toList());
                return response;
        }).toList());


    }

    @GetMapping( "/{id}")
    public ResponseEntity<GetSupplierResponse>getSupplierById(@PathVariable int id){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier.isPresent()){
            GetSupplierResponse response = new GetSupplierResponse(supplier.get());
            List<Product>productList = supplier.get().getProducts();
            response.setProducts(productList.stream().map(ProductResponse::new).toList());
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
        
    @PutMapping("/{id}")
    ResponseEntity<UpdateSupplierResponse>updateSupplierById(@PathVariable int id, @RequestBody UpdateSupplierRequest request){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier.isPresent()){
            Supplier updatedSupplier = supplier.get();
            System.out.println("Before Update:"+updatedSupplier);
            updatedSupplier.setName(request.getName());
            updatedSupplier.setPhone(request.getPhone());
            updatedSupplier.setAddress(request.getAddress());
            updatedSupplier.setEmail(request.getEmail());
            System.out.println("Before Save:"+updatedSupplier);
            updatedSupplier = supplierRepository.save(updatedSupplier);
            UpdateSupplierResponse response = new UpdateSupplierResponse(updatedSupplier);
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<CreateSupplierResponse>createSupplier(@RequestBody CreateSupplierRequest request){
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());
        supplier.setEmail(request.getEmail());
        System.out.println("Before Save:"+supplier);
        Supplier savedSupplier = supplierRepository.save(supplier);
        CreateSupplierResponse response = new CreateSupplierResponse(savedSupplier);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>DeleteSupplierById(@PathVariable int id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier.isPresent()){
            supplierRepository.delete(supplier.get());
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
