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

    @OneToOne(fetch = FetchType.LAZY)
    private  Answer answer;

    @Builder
    public Question(String title, String content, int likeCnt, int hits, Follower follower) {
        super(title, content, likeCnt, hits);
        this.follower = follower;
    }

    public void setFollower(Follower follower) {
        this.follower = follower;
    }

}
