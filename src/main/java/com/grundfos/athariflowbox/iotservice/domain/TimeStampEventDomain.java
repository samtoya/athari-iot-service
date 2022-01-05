package com.grundfos.athariflowbox.iotservice.domain;

import com.grundfos.athariflowbox.iotservice.event.Event;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TimeStampEventDomain {
    private LocalDateTime timestamp;

    public static TimeStampEventDomain mapEventToDomain(Event event) {
        return TimeStampEventDomain.builder()
                .timestamp(event.getCreatedAt())
                .build();
    }
}
