package lk.zerocode.sms.api.projection;

public interface StudentAttendanceMonthlyProjection {

    String getYearMonth(); // Year and month as a string (e.g., "2024-01")
    long getPresentCount();
    long getLateCount();
    long getAbsentCount();
}
