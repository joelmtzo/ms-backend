package com.fwtec.eventservice.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Event {

    @Id
    private String eventId;
    private String operation;
    private String data;
}
