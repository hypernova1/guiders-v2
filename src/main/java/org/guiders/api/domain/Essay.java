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

    @Builder
    public Essay(String title, String content, EssayType essayType, Guider guider) {
        super(title, content, 0, 0);
        this.essayType = essayType;
        this.guider = guider;
    }
}
