package org.gachon.vacation;

import java.util.Date;

import org.gachon.employee.Employee;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 휴가 정보를 담는 클래스
 */
@Getter
@Setter
public class Vacation {
    @NonNull
    private Employee employee;
    @NonNull
    private Date startDate;
    @NonNull
    private Date endDate;
    private String reason = "";
    private boolean approved;
    private boolean canceled;

    public Vacation(Employee employee, Date startDate, Date endDate, String reason) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.approved = false;
        this.canceled = false;
    }

    protected void setApproved(boolean approved) {
        this.approved = approved;
    }
}
