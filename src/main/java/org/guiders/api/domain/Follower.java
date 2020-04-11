package org.guiders.api.domain;

import lombok.Getter;
import lombok.Setter;
import org.guiders.api.domain.audit.DateAudit;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter @Setter
@Entity
@DiscriminatorValue("F")
public class Follower extends DateAudit {
}
