package com.fwtec.products.api.query.controller;

import com.fwtec.products.api.command.model.ProductRestModel;
import com.fwtec.products.api.query.queries.GetProductQuery;
import com.fwtec.products.api.query.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductQueryController {

    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getAll() {
        GetProductsQuery getProductsQuery = new GetProductsQuery();

        List<ProductRestModel> productRestModels =
                queryGateway.query(getProductsQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();

        return productRestModels;
    }

    @GetMapping(value = "/{productId}")
    public ProductRestModel get(@PathVariable String productId) {
        GetProductQuery getProductQuery = new GetProductQuery(productId);

        ProductRestModel productRestModel =
                queryGateway.query(getProductQuery, ResponseTypes.instanceOf(ProductRestModel.class)).join();

        return productRestModel;
    }
}
