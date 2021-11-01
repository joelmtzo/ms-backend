package com.fwtec.eventservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class EventRestModel {

    private String eventId;
    private String operation;
    private String data;
}
