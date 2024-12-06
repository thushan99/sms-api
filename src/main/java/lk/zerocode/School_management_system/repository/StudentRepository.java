package lk.zerocode.School_management_system.repository;

import lk.zerocode.School_management_system.model.Gender;
import lk.zerocode.School_management_system.model.Status;
import lk.zerocode.School_management_system.model.Student;
import lk.zerocode.School_management_system.projection.GradeSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStatus(Status status);

    Student findTopByOrderByIdDesc();

    @Query("SELECT g.gradeName AS grade, g.gradeName AS gradeName, t.teacherName AS teacherName, " +
            "SUM(CASE WHEN s.gender = :female THEN 1 ELSE 0 END) AS femaleCount, " +
            "SUM(CASE WHEN s.gender = :male THEN 1 ELSE 0 END) AS maleCount " +
            "FROM Student s " +
            "JOIN s.grade g " +
            "JOIN g.teacher t " +
            "GROUP BY g.gradeName, g.gradeName, t.teacherName")
    List<GradeSummaryProjection> findGradeSummaries(@Param("female") Gender female, @Param("male") Gender male);

}