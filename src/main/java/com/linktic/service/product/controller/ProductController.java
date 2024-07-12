package com.linktic.service.product.controller;

import com.linktic.service.product.entity.Product;
import com.linktic.service.product.service.ProductServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.linktic.service.product.util.Constant.*;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceI productServiceI;

    @GetMapping
    public ResponseEntity<List<Product>> obtenerTodosLosProductos() {
        try {
            List<Product> products = productServiceI.obtenerTodosLosProductos();
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<Map<String, Object>> guardarProducto(@RequestBody Product product) {
        Map<String, Object> response = new HashMap<>();
        try {
            Product savedProduct = productServiceI.guardarProducto(product);
            if (savedProduct != null && savedProduct.getProductId() != null) {
                response.put(ESTATUS, HttpStatus.OK);
                response.put(MESSEGUE, "Producto guardado exitosamente");
                response.put(PRODUCT, savedProduct);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                response.put(ESTATUS, HttpStatus.BAD_REQUEST);
                response.put(MESSEGUE, "Error al guardar el producto");
                response.put(PRODUCT, null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.put(MESSEGUE, "Error al guardar el producto");
            response.put("cause", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerProductoPorId(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {


        Product product = productServiceI.obtenerProductoPorId(id);
        if (product != null) {
            response.put(ESTATUS, HttpStatus.OK);
            response.put(MESSEGUE, "Producto ha obtenido exitosamente");
            response.put(PRODUCT, product);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            response.put(ESTATUS, HttpStatus.NOT_FOUND);
            response.put(MESSEGUE, "Error al obtener el producto");
            response.put(PRODUCT, null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        }catch (Exception e) {
            response.put(ESTATUS, HttpStatus.INTERNAL_SERVER_ERROR);
            response.put(MESSEGUE, "Ocurrió un error al procesar la solicitud");
            response.put("cause", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        try {
            productServiceI.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
