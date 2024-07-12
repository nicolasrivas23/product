package com.linktic.service.product.controller;

import com.linktic.service.product.entity.Product;
import com.linktic.service.product.service.ProductServiceI;
import com.linktic.service.product.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductServiceI productService;

    @InjectMocks
    private ProductController productController;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("1");
        product.setNombre("Test Product");
    }

    @Test
    void testObtenerTodosLosProductos() {
        when(productService.obtenerTodosLosProductos()).thenReturn(Collections.singletonList(product));

        ResponseEntity<List<Product>> response = productController.obtenerTodosLosProductos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
        verify(productService, times(1)).obtenerTodosLosProductos();
    }



    @Test
    void testObtenerTodosLosProductos_Exception() {
        when(productService.obtenerTodosLosProductos()).thenThrow(new RuntimeException("Error"));

        ResponseEntity<List<Product>> response = productController.obtenerTodosLosProductos();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(productService, times(1)).obtenerTodosLosProductos();
    }

    @Test
    void testGuardarProducto() {
        when(productService.guardarProducto(any(Product.class))).thenReturn(product);

        ResponseEntity<Map<String, Object>> response = productController.guardarProducto(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Producto guardado exitosamente", response.getBody().get(Constant.MESSEGUE));
        assertEquals(product, response.getBody().get(Constant.PRODUCT));
        verify(productService, times(1)).guardarProducto(any(Product.class));
    }

    @Test
    void testGuardarProducto_IllegalArgumentException() {
        when(productService.guardarProducto(any(Product.class))).thenThrow(new IllegalArgumentException("Error"));

        ResponseEntity<Map<String, Object>> response = productController.guardarProducto(product);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error", response.getBody().get(Constant.MESSEGUE));
        verify(productService, times(1)).guardarProducto(any(Product.class));
    }

    @Test
    void testGuardarProducto_Exception() {
        when(productService.guardarProducto(any(Product.class))).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Map<String, Object>> response = productController.guardarProducto(product);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al guardar el producto", response.getBody().get(Constant.MESSEGUE));
        assertEquals("Error", response.getBody().get("cause"));
        verify(productService, times(1)).guardarProducto(any(Product.class));
    }

    @Test
    void testObtenerProductoPorId() {
        when(productService.obtenerProductoPorId("1")).thenReturn(product);

        ResponseEntity<Map<String, Object>> response = productController.obtenerProductoPorId("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Producto obtenido exitosamente", response.getBody().get(Constant.MESSEGUE));
        assertEquals(product, response.getBody().get(Constant.PRODUCT));
        verify(productService, times(1)).obtenerProductoPorId("1");
    }

    @Test
    void testObtenerProductoPorId_NotFound() {
        when(productService.obtenerProductoPorId("1")).thenReturn(null);

        ResponseEntity<Map<String, Object>> response = productController.obtenerProductoPorId("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Producto no encontrado", response.getBody().get(Constant.MESSEGUE));
        verify(productService, times(1)).obtenerProductoPorId("1");
    }

    @Test
    void testObtenerProductoPorId_Exception() {
        when(productService.obtenerProductoPorId("1")).thenThrow(new RuntimeException("Error"));

        ResponseEntity<Map<String, Object>> response = productController.obtenerProductoPorId("1");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocurrió un error al procesar la solicitud", response.getBody().get(Constant.MESSEGUE));
        assertEquals("Error", response.getBody().get("cause"));
        verify(productService, times(1)).obtenerProductoPorId("1");
    }

    @Test
    void testEliminarProducto() {
        doNothing().when(productService).eliminarProducto("1");

        ResponseEntity<Void> response = productController.eliminarProducto("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).eliminarProducto("1");
    }

    @Test
    void testEliminarProducto_Exception() {
        doThrow(new RuntimeException("Error")).when(productService).eliminarProducto("1");

        ResponseEntity<Void> response = productController.eliminarProducto("1");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(productService, times(1)).eliminarProducto("1");
    }
}
