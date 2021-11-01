package com.fwtec.products.api.query.projection;

import com.fwtec.products.api.command.data.Product;
import com.fwtec.products.api.command.data.ProductRepository;
import com.fwtec.products.api.command.model.ProductRestModel;
import com.fwtec.products.api.query.queries.GetProductQuery;
import com.fwtec.products.api.query.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery) {
        List<Product> products = productRepository.findAll();

        List<ProductRestModel> productRestModels =
                products.stream()
                        .map(product -> ProductRestModel
                                .builder()
                                .productId(product.getProductId())
                                .name(product.getName())
                                .measureUnit(product.getMeasureUnit())
                                .code(product.getCode())
                                .price(product.getPrice())
                                .build())
                        .collect(Collectors.toList());

        return productRestModels;
    }

    @QueryHandler
    public ProductRestModel handle(GetProductQuery getProductQuery) {
        Product product = productRepository.getById(getProductQuery.getProductId());

        ProductRestModel productRestModel = ProductRestModel.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .measureUnit(product.getMeasureUnit())
                .code(product.getCode())
                .price(product.getPrice())
                .build();

        return productRestModel;
    }
}
