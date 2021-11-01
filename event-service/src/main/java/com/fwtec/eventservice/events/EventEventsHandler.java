package com.fwtec.eventservice.events;

import com.fwtec.coreapis.events.EventCreatedEvent;
import com.fwtec.eventservice.data.Event;
import com.fwtec.eventservice.data.EventRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("event")
public class EventEventsHandler {

    private EventRepository eventRepository;

    public EventEventsHandler(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @EventHandler
    public void on(EventCreatedEvent eventCreatedEvent) {
        Event event = new Event();

        BeanUtils.copyProperties(eventCreatedEvent, event);

        eventRepository.save(event);
    }
}
