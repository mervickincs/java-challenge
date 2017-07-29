package com.avenue.product.support;

import com.avenue.order.support.OrderProduct;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products_info")
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_id", unique = true)
    private int productId;
    @Column(name = "product_name", length = 10)
    private String productName;

    @Column(name = "unit_price",length = 10)
    private float  unitPrice;

    @Column(name = "units_in_stock",length = 10)
    private int unitsInStock;

    @Transient
    private int quantity;
    @JsonBackReference
    @OneToMany(mappedBy = "pk.product",fetch = FetchType.LAZY)
    private Set<OrderProduct> orderProductSet=new HashSet<OrderProduct>();

    public Product()
    {

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(String productName, float unitPrice, int unitsInStock) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
    }

    public Product(String productName, float unitPrice, int unitsInStock, Set<OrderProduct> orderProductSet) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.orderProductSet = orderProductSet;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }


    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }


    public Set<OrderProduct> getOrderProductSet() {
        return orderProductSet;
    }

    public void setOrderProductSet(Set<OrderProduct> orderProductSet) {
        this.orderProductSet = orderProductSet;
    }
}
