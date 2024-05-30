package org.gachon.workrecord;

import java.util.List;
import java.time.LocalDateTime;

import org.gachon.employee.Employee;

public interface WorkRecordRepository {
    public void save(WorkRecord workRecord);

    public void delete(WorkRecord workRecord);

    public void update(WorkRecord workRecord);

    public List<WorkRecord> findByEmployee(Employee employee);

    public List<WorkRecord> findAll();

    public List<WorkRecord> findByEmployeeAndBetweenDate(Employee employee, LocalDateTime startDate,
            LocalDateTime endDate);
}
