package com.avenue.order.support;

import com.avenue.product.support.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "order_product")
@AssociationOverrides({
@AssociationOverride(name = "pk.order",
        joinColumns = @JoinColumn(name = "order_id")),
@AssociationOverride(name = "pk.product",
        joinColumns = @JoinColumn(name = "product_id")) })
public class OrderProduct implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private OrderProductId pk=new OrderProductId();
    @Column(name = "product_quantity", length = 10)
    private int productQuantity;
    @Column(name = "unit_price", length = 10)
    private float unitPrice;
    @Column(name = "discount", length = 10)
    private float discount;
    @Transient
    private int temp;

    public OrderProduct()
    {

    }

    @Transient
    private Set<Product> productSet;

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public OrderProductId getPk() {
        return pk;
    }

    public void setPk(OrderProductId pk) {
        this.pk = pk;
    }

    @Transient
    public Order getOrder() {
        return getPk().getOrder();
    }

    public void setOrder(Order order) {
        getPk().setOrder(order);
    }

    @Transient
    public Product getProduct() {
        return getPk().getProduct();
    }

    public void setProduct(Product product) {
        getPk().setProduct(product);
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }


}
