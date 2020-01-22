package com.turkcell.crm.ordermanagement.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

    private Order order;

    @Before
    public void setup() {
        order = new Order();
        order = createOrder();
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(order.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(order.toString());
    }

    @Test
    public void equalsTest() {
        Order documentToCompare = createOrder();
        Order nullRequest = null;
        Object nullObject = null;
        assertTrue(documentToCompare.equals(order) && order.equals(documentToCompare));
        assertFalse(order.equals(nullRequest));
        assertFalse(order.equals(nullObject));
    }

    private Order createOrder() {
        Order order = new Order();
        order.setStaticIP("XDHJ7846");
        order.setCustomer(createCustomer());
        order.setCatalog(createCatalog());
        return order;
    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setIdentityNumber("XDHJ7846");
        customer.setName("Fiber Internet");
        return customer;
    }

    private Catalog createCatalog() {
        Catalog catalog = new Catalog();
        catalog.setCode("XDHJ7846");
        catalog.setName("Fiber Internet");
        catalog.setPrice(25.0);
        return catalog;
    }

}
