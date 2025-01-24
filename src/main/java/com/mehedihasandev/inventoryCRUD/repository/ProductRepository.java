package com.mehedihasandev.inventoryCRUD.repository;

import com.mehedihasandev.inventoryCRUD.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(String id);
}
