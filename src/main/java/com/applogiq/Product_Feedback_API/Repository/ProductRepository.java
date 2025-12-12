package com.applogiq.Product_Feedback_API.Repository;

import com.applogiq.Product_Feedback_API.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findBySku(String sku);
}
