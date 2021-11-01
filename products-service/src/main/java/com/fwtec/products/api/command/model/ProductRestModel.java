package com.fwtec.products.api.command.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRestModel {

    private String productId;
    private String name;
    private String measureUnit;
    private String code;
    private String price;
}
