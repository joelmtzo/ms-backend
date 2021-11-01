package com.fwtec.products.api.command.sagas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fwtec.coreapis.commands.CreateEventCommand;
import com.fwtec.coreapis.events.EventCreatedEvent;
import com.fwtec.coreapis.events.ProductCreatedEvent;
import com.fwtec.coreapis.events.ProductDeletedEvent;
import com.fwtec.coreapis.events.ProductUpdatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class ProductManagementSaga {

    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "productId")
    public void handle(ProductCreatedEvent productCreatedEvent) throws JsonProcessingException{
        System.out.println("Saga invoked");
        String eventId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("eventId", eventId);

        commandGateway.send(new CreateEventCommand(eventId, "create", new ObjectMapper().writeValueAsString(productCreatedEvent)));
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "productId")
    public void handle(ProductUpdatedEvent productUpdatedEvent) throws JsonProcessingException {
        String eventId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("eventId", eventId);

        commandGateway.send(new CreateEventCommand(eventId, "update", new ObjectMapper().writeValueAsString(productUpdatedEvent)));
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "productId")
    public void handle(ProductDeletedEvent productDeletedEvent) throws JsonProcessingException {
        String eventId = UUID.randomUUID().toString();

        SagaLifecycle.associateWith("eventId", eventId);

        commandGateway.send(new CreateEventCommand(eventId, "delete", new ObjectMapper().writeValueAsString(productDeletedEvent)));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "eventId")
    public void handle(EventCreatedEvent eventCreatedEvent) {
        System.out.println("Saga EventCreatedEvent - ended");
    }
}
