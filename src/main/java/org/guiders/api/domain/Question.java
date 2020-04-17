package org.guiders.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
public class Question extends Post {

    @ManyToOne
    private Follower follower;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "question")
    private Answer answer;

    private int hits;
    private int lickCnt;

    @Builder
    public Question(String title, String content, Follower follower) {
        super(title, content);
        this.follower = follower;
    }

    public void setFollower(Follower follower) {
        this.follower = follower;
    }
    public void setAnswer(Answer answer) {
        answer.setQuestion(this);
        this.answer = answer;
    }

}
