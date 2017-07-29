package com.avenue;

import com.avenue.order.support.OrderProduct;
import com.avenue.product.support.Product;
import com.avenue.order.support.Order;
import com.avenue.order.support.OrderRepository;
import com.avenue.product.support.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class JavaassessmentApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(JavaassessmentApplication.class);
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private  ProductService productService;

	/*
	This is the staring point of the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(JavaassessmentApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception {

		logger.info("mock entries in database");
		Product productA = new Product("lux",12,12);
		Product productB = new Product("ad",12,32);

		productService.addProduct(productA);
		productService.addProduct(productB);

		String strdate = "29/07/2017";
		Date inputdate=new SimpleDateFormat("dd/MM/yyyy").parse(strdate);

		Order order=new Order(inputdate);
		order.setShipCountry("US");
		order.setShipAddress("2175 Tonee Dr");
		order.setShipCity("Chicago");

		HashSet<OrderProduct> orderProductHashSet =new HashSet<>();

		OrderProduct product=new OrderProduct();
		product.setOrder(order);
		product.setProduct(productA);
		product.setProductQuantity(51);
		product.setDiscount(30);
		OrderProduct product1=new OrderProduct();
		product1.setOrder(order);
		product1.setProduct(productB);
		product1.setProductQuantity(15);
		product1.setDiscount(40);
		orderProductHashSet.add(product);
		orderProductHashSet.add(product1);

		order.setOrderProductSet(orderProductHashSet);
		orderRepository.save(order);

	}

}
