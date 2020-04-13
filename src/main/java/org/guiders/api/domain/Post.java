package org.guiders.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.guiders.api.domain.audit.DateAudit;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class Post extends DateAudit {

    private String title;
    @Lob
    private String content;
    private Long likeCnt;
    private Long hits;

}
