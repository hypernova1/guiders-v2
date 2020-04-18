package org.guiders.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("F")
@NoArgsConstructor
public class Follower extends Account {

    @OneToMany(mappedBy = "writer")
    private List<Question> questions;

    @Builder
    public Follower(String email, String username, String password, String userType) {
        super(email, username, password, userType);
    }

    public void addQuestion(Question question) {
        question.setFollower(this);
        questions.add(question);
    }
}
