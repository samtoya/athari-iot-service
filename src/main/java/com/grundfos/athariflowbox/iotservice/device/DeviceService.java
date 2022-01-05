package com.grundfos.athariflowbox.iotservice.device;

import org.springframework.stereotype.Service;

@Service
public record DeviceService(DeviceRepository deviceRepository) {

}
