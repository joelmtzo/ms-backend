package com.fwtec.coreapis.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventCreatedEvent {

    private String eventId;
    private String operation;
    private String data;
}
