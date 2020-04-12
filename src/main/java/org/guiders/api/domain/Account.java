package org.guiders.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.guiders.api.domain.audit.DateAudit;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@Getter @Setter
@ToString
public abstract class Account extends DateAudit {

    @Column(unique = true, updatable = false)
    private String email;
    private String username;
    private String password;

}
