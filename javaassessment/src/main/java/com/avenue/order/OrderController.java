    package com.avenue.order;

    import com.avenue.order.support.OrderProduct;
    import com.avenue.order.support.OrderProductRepository;
    import com.avenue.order.support.OrderService;
    import com.avenue.product.support.Product;
    import com.avenue.order.support.Order;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Set;

    @RestController
    @RequestMapping(path = "/orders")
    public class OrderController {


        private final OrderService orderService;
        private final OrderProductRepository orderProductRepository;

        /*
        This constructor initializes the elements of the class Order service.
         */
        @Autowired
        public OrderController(OrderService orderService,OrderProductRepository orderProductRepository) {
            this.orderService = orderService;
            this.orderProductRepository=orderProductRepository;
        }

        // This method use to add order into database
        @PostMapping
        public void addOrder(@RequestBody Order order)
        {
            orderService.addOrder(order);
        }

        //This method use to get all orders
        @RequestMapping(method = RequestMethod.GET)
        List<Order> getAllOrders()
        {
            return orderService.getAllOrders();
        }

        /*This method use to get order based on orderID
         */

        @RequestMapping(method = RequestMethod.GET,value = "/{orderId}")
        public Order getOrderById(@PathVariable int orderId)
        {
            return orderService.getOrderById(orderId);
        }


        // This method use to delete order based on orderID
        @DeleteMapping("/{orderId}")
        public void deleteOrder(@PathVariable int orderId)
        {
            orderService.deleteOrder(orderId);
        }

        /*
        This method use to get all products based on their orderID
         */
        @GetMapping("/{orderId}/products")
        public Set<OrderProduct> getAllProductsByOrderId(@PathVariable int orderId)
        {
            return orderService.getAllProductsByOrderId(orderId);
        }

        /*This method use to get product based on their orderID and productID
         */
        @GetMapping("/{orderId}/products/{productId}")
        public OrderProduct getAllProductsByOrderId(@PathVariable int orderId,@PathVariable int productId)
        {
                return orderService.getProductByOrderIdAndProductId(orderId,productId);
        }

        /*This method use to delete product based on their orderID and productID
         */
        @DeleteMapping("/{orderId}/products/{productId}")
        public void deleteOrderByProductId(@PathVariable int orderId,@PathVariable int productId)
        {
             orderService.deleteOrderByProductId(orderId,productId);
        }

        /*
        This method use to update order quantity based on their orderID and productID
         */
        @PutMapping("/{orderId}/products/{productId}")
        public void updateOrderByProductId(@PathVariable int orderId,@PathVariable int productId,@RequestBody Product product)
        {
            orderService.updateOrder(orderId,productId,product.getQuantity());
        }
    }