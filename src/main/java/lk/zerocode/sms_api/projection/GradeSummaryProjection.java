package lk.zerocode.sms_api.projection;

public interface GradeSummaryProjection {
    String getGrade();
    String getGradeName();
    String getTeacherName();
    Long getFemaleCount();
    Long getMaleCount();
}
