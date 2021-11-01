package com.fwtec.coreapis.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateProductCommand {

    @TargetAggregateIdentifier
    private String productId;
    private String name;
    private String measureUnit;
    private String code;
    private String price;
}
