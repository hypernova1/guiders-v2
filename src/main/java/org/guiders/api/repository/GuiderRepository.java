package org.guiders.api.repository;

import org.guiders.api.domain.Guider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuiderRepository extends JpaRepository<Guider, Long> {
    int countByEmail(String email);

    Optional<Guider> findByEmailAndPassword(String email, String password);
}
