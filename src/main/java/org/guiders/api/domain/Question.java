package org.guiders.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.guiders.api.payload.QuestionDto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
public class Question extends Post {

    @ManyToOne
    private Follower writer;

    @ManyToOne
    private Guider guider;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "question")
    private Answer answer;

    private int hits;
    private int lickCnt;

    @Builder
    public Question(String title, String content, Follower writer, Guider guider) {
        super(title, content);
        this.writer = writer;
        this.guider = guider;
    }

    public void setFollower(Follower follower) {
        this.writer = follower;
    }
    public void setAnswer(Answer answer) {
        answer.setQuestion(this);
        this.answer = answer;
    }
    public void setGuider(Guider guider) {
        this.guider = guider;
    }

    public void update(QuestionDto.UpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
