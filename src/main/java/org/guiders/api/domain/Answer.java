package org.guiders.api.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.guiders.api.domain.audit.DateAudit;
import org.guiders.api.payload.AnswerDto;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "delete_flag=false")
public class Answer extends DateAudit {

    @Lob
    private String content;

    @ManyToOne
    private Guider writer;

    @OneToOne(fetch = FetchType.EAGER)
    private Question question;

    @Builder
    protected Answer(String content, Guider writer, Question question) {
        this.content = content;
        this.writer = writer;
        this.question = question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void update(AnswerDto.UpdateRequest request) {
        this.content = request.getContent();
    }

}
