package org.guiders.api.repository;

import org.guiders.api.domain.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    int countByEmailAndPassword(String email, String password);
}
