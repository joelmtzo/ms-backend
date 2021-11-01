package com.fwtec.products.api.command.controller;

import com.fwtec.coreapis.commands.CreateProductCommand;
import com.fwtec.coreapis.commands.DeleteProductCommand;
import com.fwtec.coreapis.commands.UpdateProductCommand;
import com.fwtec.products.api.command.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
public class ProductCommandController {

    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody ProductRestModel productRestModel) {
        System.out.println("ProductCommandController | createProduct");

        CreateProductCommand createProductCommand =
                CreateProductCommand.builder()
                        .productId(UUID.randomUUID().toString())
                        .name(productRestModel.getName())
                        .measureUnit(productRestModel.getMeasureUnit())
                        .code(productRestModel.getCode())
                        .price(productRestModel.getPrice())
                .build();

        return commandGateway.sendAndWait(createProductCommand);
    }

    @PutMapping(value = "/{productId}")
    public String updateProduct(@PathVariable String productId,
            @RequestBody ProductRestModel productRestModel) {
        System.out.println("ProductCommandController | updateProduct");

        UpdateProductCommand updateProductCommand =
                UpdateProductCommand.builder()
                        .productId(productId)
                        .name(productRestModel.getName())
                        .measureUnit(productRestModel.getMeasureUnit())
                        .code(productRestModel.getCode())
                        .price(productRestModel.getPrice())
                .build();

        return commandGateway.sendAndWait(updateProductCommand);
    }

    @DeleteMapping(value = "/{productId}")
    public String deleteProduct(@PathVariable String productId) {

        DeleteProductCommand deleteProductCommand =
                DeleteProductCommand
                        .builder()
                        .productId(productId)
                .build();

        return commandGateway.sendAndWait(deleteProductCommand);
    }
}
