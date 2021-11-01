package com.fwtec.products.api.command.aggregate;

import com.fwtec.coreapis.commands.CreateProductCommand;
import com.fwtec.coreapis.commands.DeleteProductCommand;
import com.fwtec.coreapis.commands.UpdateProductCommand;
import com.fwtec.coreapis.events.ProductCreatedEvent;
import com.fwtec.coreapis.events.ProductDeletedEvent;
import com.fwtec.coreapis.events.ProductUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private String measureUnit;
    private String code;
    private String price;

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand){
        System.out.println("ProductAggregate | CommandHandler | CreateProductCommand");
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventSourcingHandler
    protected void on(ProductCreatedEvent productCreatedEvent) {
        System.out.println("ProductAggregate | EventSourcingHandler | ProductCreatedEvent");
        this.productId = productCreatedEvent.getProductId();
        this.name = productCreatedEvent.getName();
        this.measureUnit = productCreatedEvent.getMeasureUnit();
        this.code = productCreatedEvent.getCode();
        this.price = productCreatedEvent.getPrice();
    }

    @CommandHandler
    public void handle(UpdateProductCommand updateProductCommand) {
        System.out.println("ProductAggregate | CommandHandler | UpdateProductCommand");
        ProductUpdatedEvent productUpdatedEvent = new ProductUpdatedEvent();

        BeanUtils.copyProperties(updateProductCommand, productUpdatedEvent);

        AggregateLifecycle.apply(productUpdatedEvent);
    }

    @EventSourcingHandler
    protected void on(ProductUpdatedEvent productUpdatedEvent) {
        System.out.println("ProductAggregate | EventSourcingHandler | ProductUpdatedEvent");
        this.productId = productUpdatedEvent.getProductId();
        this.name = productUpdatedEvent.getName();
        this.measureUnit = productUpdatedEvent.getMeasureUnit();
        this.code = productUpdatedEvent.getCode();
        this.price = productUpdatedEvent.getPrice();
    }

    @CommandHandler
    public void handle(DeleteProductCommand deleteProductCommand) {
        System.out.println("ProductAggregate | CommandHandler | DeleteProductCommand");
        ProductDeletedEvent productDeletedEvent = new ProductDeletedEvent();

        BeanUtils.copyProperties(deleteProductCommand, productDeletedEvent);

        AggregateLifecycle.apply(productDeletedEvent);
    }

    @EventSourcingHandler
    protected void on(ProductDeletedEvent productDeletedEvent) {
        System.out.println("ProductAggregate | EventSourcingHandler | ProductDeletedEvent");
        this.productId = productDeletedEvent.getProductId();
    }

}
