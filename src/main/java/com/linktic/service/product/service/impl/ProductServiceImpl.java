package com.linktic.service.product.service.impl;

import com.amazonaws.services.iot.model.SqlParseException;
import com.linktic.service.product.entity.Product;
import com.linktic.service.product.repository.ProductRespository;
import com.linktic.service.product.service.ProductServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        try {
            if (product.getProductId() != null) {
                Optional<Product> productValidated  = productRepository.findById(product.getProductId());
                if (productValidated.isPresent() && product.getProductId().equals(productValidated.get().getProductId())) {
                    throw  new SqlParseException("Error al guardar el producto, producto ya existente");
                }
            }
            return productRepository.save(product);
        }catch (Exception e) {
            throw  new SqlParseException("Error al guardar el producto" + e.getMessage());
        }
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
