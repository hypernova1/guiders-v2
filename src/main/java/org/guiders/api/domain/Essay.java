package org.guiders.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.guiders.api.constant.EssayType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Essay extends Post {

    @Enumerated(EnumType.STRING)
    private EssayType essayType;

    @ManyToOne
    private Guider guider;

    protected int likeCnt;
    protected int hits;

    @Builder
    public Essay(String title, String content, EssayType essayType, Guider guider) {
        super(title, content);
        this.essayType = essayType;
        this.guider = guider;
    }
}
