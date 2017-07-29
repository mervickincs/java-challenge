package com.avenue.order.support;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "orders_info")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", unique = true)
    private int orderId;
    @Temporal(TemporalType.DATE)
    @Column(name = "order_date",length = 10)
    private Date orderDate;
    @Column(name = "ship_address",length = 30)
    private String shipAddress;
    @Column(name = "ship_country",length = 20)
    private String shipCountry;
    @Column(name = "ship_city",length = 20)
    private String shipCity;

    @JsonBackReference
    @ElementCollection
    @OneToMany(fetch = FetchType.LAZY, targetEntity = OrderProduct.class,mappedBy = "pk.order", cascade=CascadeType.ALL)
    private Set<OrderProduct> orderProductSet=new HashSet<OrderProduct>();

    public Order()
    {

    }

    public Order(Date date)
    {
        this.orderDate=date;
    }

    public Order(String shipAddress, String shipCountry, String shipCity) {
        this.shipAddress = shipAddress;
        this.shipCountry = shipCountry;
        this.shipCity = shipCity;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }


    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }


    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }


    public Set<OrderProduct> getOrderProductSet() {
        return orderProductSet;
    }

    public void setOrderProductSet(Set<OrderProduct> orderProductSet) {
        this.orderProductSet = orderProductSet;
    }
}
