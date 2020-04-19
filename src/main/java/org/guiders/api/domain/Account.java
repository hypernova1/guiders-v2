package org.guiders.api.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.guiders.api.domain.audit.DateAudit;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public abstract class Account extends DateAudit {

    @Column(unique = true, updatable = false)
    private String email;
    protected String username;
    protected String password;
    @Column(name = "user_type", insertable = false, updatable = false)
    private String userType;

    protected Account(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

}
