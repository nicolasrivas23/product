package com.linktic.service.product.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "productos")
public class Product {

    @DynamoDBHashKey(attributeName = "ProductosId")
    private String productId;
    private String nombre;
    private String descripcion;
    private double precio;
}
