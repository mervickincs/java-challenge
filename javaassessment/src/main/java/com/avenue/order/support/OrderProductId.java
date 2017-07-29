package com.avenue.order.support;

import com.avenue.product.support.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class OrderProductId implements Serializable {

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
