package org.guiders.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.guiders.api.constant.EssayType;
import org.guiders.api.domain.audit.DateAudit;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Essay extends DateAudit {

    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private EssayType essayType;

    @ManyToOne
    private Guider guider;

    private int likeCnt;

    private int hits;

    @Builder
    protected Essay(String title, String content, EssayType essayType, Guider guider) {
        this.title = title;
        this.content = content;
        this.essayType = essayType;
        this.guider = guider;
    }

    public void increaseHits() {
        this.hits++;
    }
    public void decreaseHits() {
        this.hits--;
    }
    public void increaseLikeCnt() {
        this.likeCnt++;
    }
    public void deceaseLikeCnt() {
        this.likeCnt--;
    }
}
