package lk.zerocode.School_management_system.projection;

public interface GradeSummaryProjection {
    String getGrade();
    String getGradeName();
    String getTeacherName();
    Long getFemaleCount();
    Long getMaleCount();
}
