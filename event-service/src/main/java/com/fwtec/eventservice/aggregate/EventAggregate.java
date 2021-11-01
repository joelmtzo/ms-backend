package com.fwtec.eventservice.aggregate;

import com.fwtec.coreapis.commands.CreateEventCommand;
import com.fwtec.coreapis.events.EventCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class EventAggregate {

    @AggregateIdentifier
    private String eventId;
    private String operation;
    private String data;

    public EventAggregate() {
    }

    @CommandHandler
    public EventAggregate(CreateEventCommand createEventCommand) {
        System.out.println("EventAggregate | CommandHandler | EventAggregate");
        EventCreatedEvent eventCreatedEvent = new EventCreatedEvent();

        BeanUtils.copyProperties(createEventCommand, eventCreatedEvent);

        AggregateLifecycle.apply(eventCreatedEvent);
    }

    @EventSourcingHandler
    protected void on(EventCreatedEvent eventCreatedEvent) {
        System.out.println("EventAggregate | EventSourcingHandler | EventCreatedEvent");
        this.eventId = eventCreatedEvent.getEventId();
        this.operation = eventCreatedEvent.getOperation();
        this.data = eventCreatedEvent.getData();
    }
}
