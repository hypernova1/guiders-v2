package org.guiders.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.guiders.api.domain.audit.DateAudit;
import org.guiders.api.payload.QuestionDto;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "delete_flag=false")
public class Question extends DateAudit {

    private String title;

    @Lob
    private String content;

    @ManyToOne
    private Follower writer;

    @ManyToOne
    private Guider guider;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "question")
    private Answer answer;

    private int hits;
    private int lickCnt;

    @Builder
    protected Question(String title, String content, Follower writer, Guider guider) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.guider = guider;
    }

    public void setFollower(Follower follower) {
        this.writer = follower;
    }
    public void setGuider(Guider guider) {
        this.guider = guider;
    }

    public void update(QuestionDto.UpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
