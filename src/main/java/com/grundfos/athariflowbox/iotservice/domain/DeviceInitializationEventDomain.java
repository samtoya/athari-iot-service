package com.grundfos.athariflowbox.iotservice.domain;

import com.grundfos.athariflowbox.iotservice.event.Event;
import lombok.Builder;
import lombok.Data;

import java.util.Random;

@Data
@Builder
public class DeviceInitializationEventDomain {
    private String code;

    public static DeviceInitializationEventDomain mapEntityToDomain(Event event) {
        var rnd = new Random();
        var number = rnd.nextInt(999999);
        var code = String.format("%06d", number);

        return DeviceInitializationEventDomain.builder()
                .code(code)
                .build();
    }
}
