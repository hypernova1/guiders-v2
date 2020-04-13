package org.guiders.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("G")
@ToString
@NoArgsConstructor
public class Guider extends Account {

    @OneToMany(mappedBy = "guider")
    List<Essay> essays = new ArrayList<>();

    @Builder
    public Guider(String email, String username, String password) {
        super(email, username, password);
    }

}
