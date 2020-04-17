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
public class Answer extends Post {

    @ManyToOne
    private Guider guider;

    @OneToOne(fetch = FetchType.EAGER)
    private Question question;

    @Builder
    public Answer(String title, String content, Guider guider, Question question) {
        super(title, content);
        this.guider = guider;
        this.question = question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
