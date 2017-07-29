package com.avenue.product;

import com.avenue.product.support.Product;
import com.avenue.product.support.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;

    /*
    This constructor initializes the elements of the class product service
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // This method use to add product data in database
    @PostMapping
    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }

    //This method use to get all products
    @RequestMapping(method = RequestMethod.GET)
    Collection<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    /*This method use to get product based on productID
     */

    @RequestMapping(method = RequestMethod.GET,value = "/{productId}")
    Product getProductById(@PathVariable int productId)
    {
        return productService.getProductById(productId);
    }
}