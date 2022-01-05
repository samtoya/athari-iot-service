package com.grundfos.athariflowbox.iotservice.card;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDisableDto {
    private String reason;
}
