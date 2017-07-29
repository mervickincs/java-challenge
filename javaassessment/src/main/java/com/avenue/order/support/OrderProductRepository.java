package com.avenue.order.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

/*
This is a base repository for the class Orderproduct which extends the JPA repository.
JPA repository itself has some in built functions so we don't have to perform any of the CRUD operations.
 */
public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
    @Modifying
    @Transactional
    void deleteByPkProductProductId(int orderId);

}
