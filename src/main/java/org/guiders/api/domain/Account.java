package org.guiders.api.domain;

import lombok.*;
import org.guiders.api.domain.audit.DateAudit;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public abstract class Account extends DateAudit {

    @Column(unique = true, updatable = false)
    private String email;
    private String username;
    private String password;

}
