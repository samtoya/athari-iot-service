package com.grundfos.athariflowbox.iotservice.event;

import com.grundfos.athariflowbox.iotservice.common.Auditable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "events")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event extends Auditable {
    @Id
    private String id;
    private String type;
    private String serialNumber;
    private String data;
}
