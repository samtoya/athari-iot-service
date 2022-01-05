package com.grundfos.athariflowbox.iotservice.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountInfoDomain {
    private String name;
    private BigDecimal balance;
    private Integer threshold;
}
