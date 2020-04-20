package org.guiders.api.repository;

import org.guiders.api.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("UPDATE #{#entityName} e set e.deleteFlag=true WHERE e.id=?1")
    @Modifying
    void softDelete(Long id);
}
