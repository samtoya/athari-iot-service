package com.grundfos.athariflowbox.iotservice.device;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceDto {
    private String name;
    private String serialNumber;
    private String image;
}
