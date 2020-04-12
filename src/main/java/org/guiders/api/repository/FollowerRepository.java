package org.guiders.api.repository;

import org.guiders.api.domain.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    int countByEmail(String email);

}
