package com.avenuecode.ordertest;


import com.avenue.JavaassessmentApplication;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.InputStreamReader;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class OrderTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    private static final Logger logger = LoggerFactory.getLogger(JavaassessmentApplication.class);

    /*
    This is the main entry point for server side spring support. It will be initialized every time a new test case is run.
     */
    @Before
    public void setup() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /*
    This method gets an order details when you pass in an id of the order. Then, it checks
    if the value from the json string and the value from the url is same or not.
     */
    @Test
    public void getsOrderById() throws Exception {

        String json="{\n" +
                "    \"orderId\": 1,\n" +
                "    \"orderDate\": \"2017-07-29\",\n" +
                "    \"shipAddress\": \"2175 Tonee Dr\",\n" +
                "    \"shipCountry\": \"US\",\n" +
                "    \"shipCity\": \"Chicago\"\n" +
                "}";

        mockMvc.perform(get("/orders/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(json));
    }

    /*
    This gets all the orders from the url passes and checks the value from the string. The test case is
     passes if both the strings match.
     */
    @Test
    public void getAllOrder() throws Exception {

        String json = readJSONFromFile("all-orders.json");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/orders")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(), 201);
    }

    private String readJSONFromFile(String filename) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(filename);
        if (!classPathResource.exists()) {
            Assert.fail("No resource found " + filename);
        }
        return FileCopyUtils.copyToString(new InputStreamReader(classPathResource.getInputStream()));
    }

    /*
    This method returns all the products of a particular order id.
     */
    @Test
    public void getAllProductsByOrderId() throws Exception {

        String json = readJSONFromFile("productsOfOrder.json");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/orders/1/products")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(), 201);
    }

    /*
  This method return the product of a particular order id and product id.
   */
    @Test
    public void getSingleProductByOrderIdAndProductId() throws Exception {

        String json = readJSONFromFile("productsOfOrder.json");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/orders/1/products/1")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(), 201);
    }

    /*
    This method checks if the order has been deleted or not when passes with an order id.
    If the order is deleted successfully, you will get a return status of success.
     */
    @Test
    public void deleteOrder() throws Exception {

       // String json = readJSONFromFile("productsOfOrder.json");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/orders/1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(), 201);
        logger.info("delete success");
    }

/*
This method checks if the order has been updated successfully on the basis of the product quantity.
If the quantity of the product is updated successfully you get a return message of updated successfully.
 */
    @Test
    public void updateOrder() throws Exception {

        String json="{\n" +
                "\"quantity\":26\n" +
                "}";
        // String json = readJSONFromFile("productsOfOrder.json");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/orders/1/products/1").accept(MediaType.APPLICATION_JSON).content(json
                        )
                .contentType(MediaType.APPLICATION_JSON);;

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(), 201);
        logger.info("update success");

    }

    /*
    This method checks if the order has been addedd successfully
     */
    @Test
    public void addOrder() throws Exception {

        String json="{\n" +
                "    \"shipAddress\": \"delhi\",\n" +
                "     \"shipCountry\":\"india\",\n" +
                "    \"shipCity\":\"delhi\",\n" +
                "    \"orderProductSet\": [{\n" +
                "        \"temp\":2,\n" +
                "        \"discount\": 6.0,\n" +
                "        \"unitPrice\": 30,\n" +
                "        \"productQuantity\": 10\n" +
                "    }]\n" +
                "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/orders/1/products/1").accept(MediaType.APPLICATION_JSON).content(json
                )
                .contentType(MediaType.APPLICATION_JSON);;
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(), 201);
        logger.info("add success");
    }

}
