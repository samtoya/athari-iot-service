package com.grundfos.athariflowbox.iotservice.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SensorDataDomain {
    @NotNull
    private String sensor;
    @NotNull
    private String batteryVoltage;
}
