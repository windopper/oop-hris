package org.gachon.workrecord;

import java.util.List;
import java.time.LocalDateTime;

import org.gachon.employee.Employee;

public class WorkRecordService {
    private WorkRecordRepository workRecordRepository = NaiveWorkRecordRepository.getInstance();
    private static WorkRecordService instance;

    private WorkRecordService() {
    }

    public static WorkRecordService getInstance() {
        if (instance == null) {
            instance = new WorkRecordService();
        }
        return instance;
    }

    // 출근
    public void checkIn(Employee employee) {
        WorkRecord workRecord = new WorkRecord(employee, getCurrentDateTime(), WorkRecord.WorkType.CHECK_IN);
        workRecordRepository.save(workRecord);
    }

    // 퇴근
    public void checkOut(Employee employee) {
        WorkRecord workRecord = new WorkRecord(employee, getCurrentDateTime(), WorkRecord.WorkType.CHECK_OUT);
        workRecordRepository.save(workRecord);
    }

    // 초과 근무
    public void overTime(Employee employee) {
        WorkRecord workRecord = new WorkRecord(employee, getCurrentDateTime(), WorkRecord.WorkType.OVERTIME);
        workRecordRepository.save(workRecord);
    }

    public void deleteWorkRecord(WorkRecord workRecord) {
        workRecordRepository.delete(workRecord);
    }

    public List<WorkRecord> getWorkRecords(Employee employee) {
        return workRecordRepository.findByEmployee(employee);
    }

    public List<WorkRecord> getWorkRecords(Employee employee, LocalDateTime startDate, LocalDateTime endDate) {
        return workRecordRepository.findByEmployeeAndBetweenDate(employee, startDate, endDate);
    }

    private LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
