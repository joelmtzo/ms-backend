package com.fwtec.coreapis.commands;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@NoArgsConstructor
public class CreateEventCommand {

    @TargetAggregateIdentifier
    private String eventId;
    private String operation;
    private String data;

    public CreateEventCommand(String eventId, String operation, String data) {
        this.eventId = eventId;
        this.operation = operation;
        this.data = data;
    }
}
