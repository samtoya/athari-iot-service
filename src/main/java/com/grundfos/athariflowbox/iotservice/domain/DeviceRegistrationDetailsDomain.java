package com.grundfos.athariflowbox.iotservice.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceRegistrationDetailsDomain {
    private String name;
    private String serialNumber;
}
