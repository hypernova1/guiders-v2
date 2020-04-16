package org.guiders.api.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
public class Answer extends Post {

    @ManyToOne
    private Guider guider;

    @OneToOne(fetch = FetchType.EAGER)
    private Question question;
}
