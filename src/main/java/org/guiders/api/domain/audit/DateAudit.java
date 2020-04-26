package org.guiders.api.domain.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class DateAudit {

    @Id @GeneratedValue
    private Long id;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant updatedDate;

    @Column(name = "delete_flag")
    private boolean deleteFlag;

}
