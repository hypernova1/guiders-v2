package org.guiders.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.guiders.api.domain.audit.DateAudit;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@Getter @Setter
public class Account extends DateAudit {

    @Column(unique = true, updatable = false)
    private String email;
    private String username;
    private String password;

}
