package com.gtalent.tutor.Controller;

import com.gtalent.tutor.models.Product;
import com.gtalent.tutor.models.User;
import com.gtalent.tutor.models.request.CreateProductRequest;
import com.gtalent.tutor.models.request.CreateUserRequest;
import com.gtalent.tutor.models.request.UpdateProductRequest;
import com.gtalent.tutor.repositories.ProductRepository;
import com.gtalent.tutor.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {
    //Step1.建立Product model
    //Step2.建立Product Repository extends JpaRepository
    //Step3.建立Product CRUD APIs
    private final ProductRepository productRepository;
    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<GetProductResponse>> getAllProducts(){
        List<Product>Products = productRepository.findAll();
        return ResponseEntity.ok(Products.stream().map(GetProductResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponse>getProductById(@PathVariable int id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            GetProductResponse response = new GetProductResponse(product.get());
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateProductResponse>updateProductsById(@PathVariable int id, @RequestBody UpdateProductRequest request){
        //1.找到product
        Optional <Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            //2.確定找到product之後
            Product updatedProduct = product.get();
            System.out.println("Before Update:"+updatedProduct);
            //3.將欲更新資料填充至對應product
            updatedProduct.setName(request.getName());
            updatedProduct.setPrice(request.getPrice());
            updatedProduct.setQuantity(request.getQuantity());
            System.out.println("Before Save:"+updatedProduct);
            updatedProduct = productRepository.save(updatedProduct);
            UpdateProductResponse response = new UpdateProductResponse(updatedProduct);
            return ResponseEntity.ok(response);
        }else
        {return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreateProductResponse>createUser(@RequestBody CreateProductRequest request){
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setStatus(request.getStatus());
        System.out.println("Before Save:"+product);
        Product savedProduct = productRepository.save(product);
        CreateProductResponse response = new CreateProductResponse(savedProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>DeleteProductById(@PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.delete(product.get());
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
