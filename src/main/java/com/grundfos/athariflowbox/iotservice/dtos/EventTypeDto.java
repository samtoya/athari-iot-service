package com.grundfos.athariflowbox.iotservice.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class EventTypeDto {
    @NotNull
    private String type;
    @NotNull
    private String serialNumber;
    @NotNull
    private Map<String, Object> data;
}
