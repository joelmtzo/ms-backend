package com.fwtec.products.api.command.data;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Product {

    @Id
    private String productId;
    private String name;
    private String measureUnit;
    private String code;
    private String price;

}
