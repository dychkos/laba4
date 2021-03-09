package unrgo;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {
    Catalog catalog;
    @BeforeEach
    void createCatalog(){
        catalog = new Catalog("Iphone 7s","Apple",15000,200);
    }

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("Iphone 7s",catalog.getName());
    }

    @org.junit.jupiter.api.Test
    void getProducer() {
        assertEquals("Apple",catalog.getProducer());

    }

    @org.junit.jupiter.api.Test
    void getCount() {
        assertEquals(200,catalog.getCount());

    }

    @org.junit.jupiter.api.Test
    void setCount() {
        catalog.setCount(150);
        assertEquals(150,catalog.getCount());
    }

    @org.junit.jupiter.api.Test
    void deleteGood() {
        ArrayList<Catalog> list = new ArrayList<Catalog>();
        list.add(catalog);
        catalog.deleteGood(list);
        assertEquals(0,list.size());


    }

    @org.junit.jupiter.api.Test
    void showCatalog() {
    }

    @org.junit.jupiter.api.Test
    void saveCatalogToBase() {
    }

    @org.junit.jupiter.api.Test
    void reduceCount() {
    }

    @org.junit.jupiter.api.Test
    void getCatalogFromBase() {
    }

    @org.junit.jupiter.api.Test
    void searchGood() {
    }

    @org.junit.jupiter.api.Test
    void adminMode() {
    }
}