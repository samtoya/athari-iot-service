package com.grundfos.athariflowbox.iotservice.device;

import com.grundfos.athariflowbox.iotservice.common.Auditable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "devices")
public class Device extends Auditable {
    @Id
    private String id;
    private String name;
    private String passcode;
    private String serialNumber;
}
