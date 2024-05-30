package org.gachon.workrecord;

import java.time.LocalDateTime;
import org.gachon.employee.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkRecord {
    private Employee employee;
    private LocalDateTime date;
    private WorkType workType;

    public enum WorkType {
        CHECK_IN,
        CHECK_OUT,
        OVERTIME
    }
}
