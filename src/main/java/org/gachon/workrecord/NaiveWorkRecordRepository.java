package org.gachon.workrecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.gachon.employee.Employee;

public class NaiveWorkRecordRepository implements WorkRecordRepository {
    private List<WorkRecord> workRecords = new ArrayList<>();
    private static NaiveWorkRecordRepository instance;

    private NaiveWorkRecordRepository() {
    }

    public static NaiveWorkRecordRepository getInstance() {
        if (instance == null) {
            instance = new NaiveWorkRecordRepository();
        }
        return instance;
    }

    @Override
    public void save(WorkRecord workRecord) {
        workRecords.add(workRecord);
    }

    @Override
    public void delete(WorkRecord workRecord) {
        workRecords.remove(workRecord);
    }

    @Override
    public void update(WorkRecord workRecord) {
        if (workRecords.contains(workRecord)) {
            workRecords.remove(workRecord);
            workRecords.add(workRecord);
        }
    }

    @Override
    public List<WorkRecord> findByEmployee(Employee employee) {
        return workRecords.stream().filter(w -> w.getEmployee().equals(employee)).toList();
    }

    @Override
    public List<WorkRecord> findAll() {
        return workRecords;
    }

    @Override
    public List<WorkRecord> findByEmployeeAndBetweenDate(Employee employee, LocalDateTime startDate,
            LocalDateTime endDate) {
        return workRecords.stream()
                .filter(w -> w.getEmployee().equals(employee))
                .filter(w -> w.getDate().isEqual(startDate)
                        || (w.getDate().isAfter(startDate) && w.getDate().isBefore(endDate)))
                .toList();
    }

}
