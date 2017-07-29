package com.avenue.order.support;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
This is a base repsoitory for the Order class which extends the JPA repository to perform all the CRUD operations.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{

    Order findByOrderId(int id);
}
