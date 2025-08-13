package com.gtalent.tutor.repositories;

import com.gtalent.tutor.models.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Override
    @EntityGraph(attributePaths = "supplier")
    List<Product> findAll();
}
