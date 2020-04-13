package org.guiders.api.domain;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("F")
public class Follower extends Account {
}
