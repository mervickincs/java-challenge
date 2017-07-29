package com.avenue.order.support;


import com.avenue.product.support.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/*
This is a service class which acts a security layer so no one could see the implementation of the function
directly.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    /*
   * This method use to get one order based on orderID and that will be on JSON format.
   * */
    public Order getOrderById(int orderID)
    {
        return orderRepository.findOne(orderID);
    }

    /*
    * This method use to get all orders from databse and that will be JSON Format.
    * */
    public List<Order> getAllOrders()
    {
        List<Order> orderList = new ArrayList<>();
        orderRepository.findAll().forEach(orderList::add);
        return orderList;
    }

    /*
    This method take a order as object and put into the database.
    use to add new order into database
     */
    public void addOrder(Order inputorder)
    {
        Order order=new Order(new Date());
        order.setShipCountry(inputorder.getShipCountry());
        order.setShipAddress(inputorder.getShipAddress());
        order.setShipCity(inputorder.getShipCity());
        Set<OrderProduct> orderProductHashSet =inputorder.getOrderProductSet();;

        for(OrderProduct orderProduct:orderProductHashSet) {
            Product product=new Product();
            product.setProductId(orderProduct.getTemp());
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProduct.setDiscount(orderProduct.getDiscount());
            orderProduct.setProductQuantity(orderProduct.getProductQuantity());

        }
        order.setOrderProductSet(orderProductHashSet);
        orderRepository.save(order);
    }

    /*
    * This method use to delete order based on orderID
    * */
    public void deleteOrder(int id)
    {
        orderRepository.delete(id);
    }

    /*
     This method use to get all products based on orderID
    */
    public Set<OrderProduct> getAllProductsByOrderId(int orderId) {

        Order order = orderRepository.findOne(orderId);
        Set<OrderProduct> orderProducts = order.getOrderProductSet();
        return orderProducts;
    }

/*
This method use to get products based on orderID and ProductID
 */
    public OrderProduct getProductByOrderIdAndProductId(int orderId,int productId) {

        Order order = orderRepository.findOne(orderId);
        OrderProduct orderProductfinal=null;
        Set<OrderProduct> orderProduct1 =     order.getOrderProductSet();
        for(OrderProduct orderProduct:orderProduct1) {
            if (orderProduct.getOrder().getOrderId() == orderId & orderProduct.getProduct().getProductId() == productId) {
                orderProductfinal = orderProduct;
            }
        }
        return orderProductfinal;
    }

    /*
    This method use to delete order based on ProductID
     */
    public void deleteOrderByProductId(int orderId,int productId) {
        orderProductRepository.deleteByPkProductProductId(orderId);
    }

    /*
    This method use to update order quantity based on orderID and productID
     */
    public void updateOrder(int orderId,int productID,int quantity)
    {
        Order order=orderRepository.findByOrderId(orderId);
        Set<OrderProduct> orderProductHashSet =order.getOrderProductSet();;
        for(OrderProduct orderProduct:orderProductHashSet) {

            if (orderProduct.getOrder().getOrderId() == orderId & orderProduct.getProduct().getProductId() == productID) {
                orderProduct.setOrder(order);
                orderProduct.setProductQuantity(quantity);
            }
        }
        order.setOrderProductSet(orderProductHashSet);
        orderRepository.save(order);
    }
}
