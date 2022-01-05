package com.grundfos.athariflowbox.iotservice.card;

import com.grundfos.athariflowbox.iotservice.common.Auditable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "cards")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Card extends Auditable {
    @Id
    private String id;
    @Column(unique = true)
    private String serialNumber;
    private String holder;
    private LocalDateTime disabledAt;
    @Column(columnDefinition = "text")
    private String reason;

    public Boolean getIsEnabled() {
        return disabledAt == null && reason == null;
    }
}
