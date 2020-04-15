package org.guiders.api.domain;

import lombok.*;
import org.guiders.api.domain.audit.DateAudit;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public abstract class Account extends DateAudit {

    @Column(unique = true, updatable = false)
    private String email;
    protected String username;
    protected String password;
    @Column(name = "user_type", insertable = false, updatable = false)
    private String userType;

    public Account(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
