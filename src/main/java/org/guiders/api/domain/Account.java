package org.guiders.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.guiders.api.domain.audit.DateAudit;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "user_type")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
public class Account extends DateAudit {

    @Column(unique = true, updatable = false)
    private String email;
    private String username;


}
