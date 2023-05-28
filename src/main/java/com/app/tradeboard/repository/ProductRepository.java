package com.app.tradeboard.repository;

import com.app.tradeboard.model.Product;
import com.app.tradeboard.utils.Enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(ProductCategory category);

}
