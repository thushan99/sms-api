package lk.zerocode.School_management_system.repository;

import lk.zerocode.School_management_system.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("SELECT g FROM Grade g JOIN FETCH g.teacher")
    List<Grade> findAllWithTeachers();
}
