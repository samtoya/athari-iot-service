package com.grundfos.athariflowbox.iotservice.event;

import com.google.gson.Gson;
import com.grundfos.athariflowbox.iotservice.domain.SensorDataDomain;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDomain<T> {
    private String id;
    private String type;
    private String serialNumber;
    private T data;
    private String sessionType;
    private double amountFetched;
    private LocalDateTime createdAt;

    public static EventDomain mapPowerEntityToDomain(Event event) {
        var sensorData = new Gson().fromJson(event.getData(), SensorDataDomain.class);
        return EventDomain.builder()
                .id(event.getId())
                .serialNumber(event.getSerialNumber())
                .data(sensorData)
                .build();
    }
}
