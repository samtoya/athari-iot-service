package com.grundfos.athariflowbox.iotservice.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDomain {
    private String id;
    private String serialNumber;
    private String holder;
    private Boolean isActive;
    private LocalDateTime disabledAt;
    private String reason;

    public static CardDomain mapEntityToDomain(Card card) {
        var domain = new CardDomain();
        BeanUtils.copyProperties(card, domain);
        return domain;
    }
}
