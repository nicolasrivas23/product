package com.linktic.service.product.repository;

import com.linktic.service.product.entity.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface ProductRespository extends CrudRepository<Product, String> {
}
