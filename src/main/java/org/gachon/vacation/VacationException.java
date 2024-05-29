package org.gachon.vacation;

public class VacationException extends Exception {
    public static final String VACATION_OVERLAP = "휴가 기간이 중복됩니다.";
    public static final String VACATION_SHORTAGE = "남은 휴가 일수가 부족합니다.";
    public static final String VACATION_APPROVED = "이미 승인된 휴가입니다.";
    public static final String VACATION_CANCELED = "이미 취소된 휴가입니다.";

    public VacationException(String message) {
        super(message);
    }
}
