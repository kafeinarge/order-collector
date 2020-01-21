package com.turkcell.crm.ordermanagement.entity;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CatalogTest {

    private Catalog catalog;

    @Before
    public void setup() {
        catalog = new Catalog();
        catalog = createCatalog();
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(catalog.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(catalog.toString());
    }

    @Test
    public void equalsTest() {
        Catalog documentToCompare = createCatalog();
        Catalog nullRequest = null;
        Object nullObject = null;
        assertTrue(documentToCompare.equals(catalog) && catalog.equals(documentToCompare));
        assertFalse(catalog.equals(nullRequest));
        assertFalse(catalog.equals(nullObject));
    }

    private Catalog createCatalog() {
        Catalog catalog = new Catalog();
        catalog.setCode("XDHJ7846");
        catalog.setName("Fiber Internet");
        catalog.setPrice(25.0);
        return catalog;
    }

}
