package lk.zerocode.sms.api.projection;

public interface GradeSummaryProjection {

    String getGrade();
    String getGradeName();
    String getTeacherName();
    Long getFemaleCount();
    Long getMaleCount();
}
