package org.guiders.api.repository;

import org.guiders.api.domain.Essay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EssayRepository extends JpaRepository<Essay, Long> {
}
