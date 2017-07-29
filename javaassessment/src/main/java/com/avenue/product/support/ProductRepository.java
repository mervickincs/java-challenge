package com.avenue.product.support;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This is the base repository for product class which implements JPA repository to perform CRUD operations.
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
