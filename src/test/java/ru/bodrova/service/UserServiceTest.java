package ru.bodrova.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserServiceTest {
    private ProductService productService;
    private ProductDao productDao;
    private Product product;
    @Before
    public void setupMock() {
        productService = new ProductService();
        product = mock(Product.class);
        productDao = mock(ProductDao.class);
        productService.setProductDao(productDao);
    }
    @Test
    public void testGetUser(){
    }

}