package com.linktic.service.product.service.impl;

import com.linktic.service.product.entity.Product;
import com.linktic.service.product.repository.ProductRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRespository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("1");
        product.setNombre("Test Product");
    }

    @Test
    void testObtenerTodosLosProductos() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));
        List<Product> products = productService.obtenerTodosLosProductos();
        assertFalse(products.isEmpty());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGuardarProducto_NuevoProducto() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product savedProduct = productService.guardarProducto(product);
        assertNotNull(savedProduct);
        assertEquals("1", savedProduct.getProductId());
        verify(productRepository, times(1)).save(any(Product.class));
    }


    @Test
    void testGuardarProducto_Exception() {
        when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Database error"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.guardarProducto(product);
        });
        assertTrue(exception.getMessage().contains("Error al guardar el producto: Database error"));
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testObtenerProductoPorId() {
        when(productRepository.findById("1")).thenReturn(Optional.of(product));
        Product foundProduct = productService.obtenerProductoPorId("1");
        assertNotNull(foundProduct);
        assertEquals("1", foundProduct.getProductId());
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testObtenerProductoPorId_NoEncontrado() {
        when(productRepository.findById("1")).thenReturn(Optional.empty());
        Product foundProduct = productService.obtenerProductoPorId("1");
        assertNull(foundProduct);
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testEliminarProducto() {
        doNothing().when(productRepository).deleteById("1");
        productService.eliminarProducto("1");
        verify(productRepository, times(1)).deleteById("1");
    }
}