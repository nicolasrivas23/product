package com.linktic.service.product.service;

import com.linktic.service.product.entity.Product;

import java.util.List;

public interface ProductServiceI {
    List<Product> obtenerTodosLosProductos();

    Product guardarProducto(Product product);

    Product obtenerProductoPorId(String id);

    void eliminarProducto(String id);
}
