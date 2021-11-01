package com.fwtec.products.api.command.events;

import com.fwtec.coreapis.events.ProductCreatedEvent;
import com.fwtec.coreapis.events.ProductDeletedEvent;
import com.fwtec.coreapis.events.ProductUpdatedEvent;
import com.fwtec.products.api.command.data.Product;
import com.fwtec.products.api.command.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        Product product = new Product();

        BeanUtils.copyProperties(productCreatedEvent, product);

        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductUpdatedEvent productUpdatedEvent) {
        System.out.println("ProductEventsHandler | EventHandler | ProductUpdatedEvent");
        Product product = new Product();

        BeanUtils.copyProperties(productUpdatedEvent, product);

        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductDeletedEvent productDeletedEvent) {
        System.out.println("ProductEventsHandler | EventHandler | ProductDeletedEvent");
        Product product = new Product();

        BeanUtils.copyProperties(productDeletedEvent, product);

        productRepository.delete(product);
    }

}
