package org.guiders.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.guiders.api.constant.RoleName;
import org.guiders.api.domain.audit.DateAudit;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends DateAudit {

    @Enumerated(EnumType.STRING)
    private RoleName name;

}
