package org.guiders.api.repository;

import org.guiders.api.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("UPDATE #{#entityName} e SET e.deleteFlag=true WHERE e.id=?1")
    @Modifying
    void softDelete(Long id);

//    @Override
//    @Query("SELECT e FROM #{#entityName} e where e.deleteFlag=false")
//    List<Question> findAll();
//
//    @Override
//    @Query("SELECT e FROM #{#entityName} e where e.deleteFlag=false")
//    Page<Question> findAll(Pageable pageable);
}
