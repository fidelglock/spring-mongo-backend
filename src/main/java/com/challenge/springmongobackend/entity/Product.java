package com.challenge.springmongobackend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document("products")
public class Product {

    @Id
    String id;
    String name;

    public Product(){ super();}
}
