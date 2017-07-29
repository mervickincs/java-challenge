package com.avenue.product.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
This is a service class which acts as a security layer to the controller. It hides the
implementation of the functions.
 */
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    /*
    This function is used to add a product in the database.
     */
    public void addProduct(Product product)
    {
        productRepository.save(product);
    }

    /*
    This function gets all the products from the catalog.
     */
    public List<Product> getAllProducts()
    {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Product getProductById(int id)
    {
        return productRepository.findOne(id);
    }
}
