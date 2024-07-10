package com.linktic.service.product.controller;

import com.linktic.service.product.entity.Product;
import com.linktic.service.product.service.ProductServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceI productServiceI;

    @GetMapping
    public List<Product> obtenerTodosLosProductos() {
        return productServiceI.obtenerTodosLosProductos();
    }
    @PostMapping
    public Product guardarProducto(@RequestBody Product product) {
        return productServiceI.guardarProducto(product);
    }
    @GetMapping("/{id}")
    public Product obtenerProductoPorId(@PathVariable String id) {
        return productServiceI.obtenerProductoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable String id) {
        productServiceI.eliminarProducto(id);
    }
}
