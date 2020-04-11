package org.guiders.api.repository;

import org.guiders.api.domain.Guider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuiderRepository extends JpaRepository<Guider, Long> {

}
