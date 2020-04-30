package org.guiders.api.domain;

import lombok.Getter;
import org.guiders.api.domain.audit.DateAudit;
import org.guiders.api.payload.CommentDto;
import org.hibernate.annotations.Fetch;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Comment extends DateAudit {

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Essay essay;

    @ManyToOne
    private Account writer;

    public void setEssay(Essay essay) {
        this.essay = essay;
    }

    public void update(CommentDto.Request commentDto) {
        this.content = commentDto.getContent();
    }
}
