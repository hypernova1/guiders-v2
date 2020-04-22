package org.guiders.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("F")
@NoArgsConstructor
public class Follower extends Account {

    @OneToMany(mappedBy = "writer")
    private List<Question> questions = new ArrayList<>();

    @Builder
    public Follower(String email, String firstName, String lastName, String password) {
        super(email, new Name(firstName, lastName), password);
    }

    public void addQuestion(Question question) {
        question.setWriter(this);
        questions.add(question);
    }
}
