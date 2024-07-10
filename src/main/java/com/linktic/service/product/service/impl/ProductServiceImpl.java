package com.linktic.service.product.service.impl;

import com.linktic.service.product.entity.Product;
import com.linktic.service.product.repository.ProductRespository;
import com.linktic.service.product.service.ProductServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServiceI {

    private final ProductRespository productRepository;

    @Override
    public List<Product> obtenerTodosLosProductos() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product guardarProducto(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product obtenerProductoPorId(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarProducto(String id) {
        productRepository.deleteById(id);
    }
}
