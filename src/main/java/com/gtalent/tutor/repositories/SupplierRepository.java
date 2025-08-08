package com.gtalent.tutor.repositories;

import com.gtalent.tutor.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
