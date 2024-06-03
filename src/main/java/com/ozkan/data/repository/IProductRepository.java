package com.ozkan.data.repository;

import com.ozkan.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    public Optional<Product> findByProductId(Long id);
}
