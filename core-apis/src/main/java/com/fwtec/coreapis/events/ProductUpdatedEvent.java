package com.fwtec.coreapis.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdatedEvent {

    private String productId;
    private String name;
    private String measureUnit;
    private String code;
    private String price;
}
