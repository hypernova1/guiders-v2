package org.guiders.api.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.guiders.api.domain.audit.DateAudit;
import org.guiders.api.model.Name;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public abstract class Account extends DateAudit {

    @Column(unique = true, updatable = false)
    private String email;

    @Embedded
    protected Name username;

    protected String password;
    @Column(name = "user_type", insertable = false, updatable = false)
    private String userType;

    private Instant lastLoginDate;

    protected Account(String email, Name username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void setUsername(Name username) {
        this.username = username;
    }

    public void updateLoginDate() {
        this.lastLoginDate = Instant.now();
    }
}
