package org.gachon.payroll;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.gachon.employee.Employee;
import org.gachon.workrecord.NaiveWorkRecordRepository;
import org.gachon.workrecord.WorkRecord;
import org.gachon.workrecord.WorkRecordRepository;

public class PayrollService {

    private WorkRecordRepository workRecordRepository = NaiveWorkRecordRepository.getInstance();
    private static PayrollService instance;

    private PayrollService() {
    }

    public static PayrollService getInstance() {
        if (instance == null) {
            instance = new PayrollService();
        }
        return instance;
    }

    /* 직원의 특정 기간 급여 계산 */
    public double calculatePay(Employee employee, LocalDateTime startDate, LocalDateTime endDate) {
        List<WorkRecord> records = workRecordRepository.findByEmployeeAndBetweenDate(employee, startDate, endDate);
        double totalHours = 0.0;
        double hourlyRate = employee.getHourlyRate();

        for (WorkRecord record : records) {
            if (record.getWorkType() == WorkRecord.WorkType.CHECK_IN) {
                LocalDateTime checkIn = record.getDate();
                LocalDateTime checkOut = findCheckOutForDate(records, checkIn, employee);
                totalHours += Duration.between(checkIn, checkOut).toHours();
            }
        }

        double grossPay = totalHours * hourlyRate;
        return grossPay - calculateDeductions(grossPay);
    }

    /* 퇴근 기록 찾기 */
    private LocalDateTime findCheckOutForDate(List<WorkRecord> records, LocalDateTime checkInDate, Employee employee) {
        return records.stream()
                .filter(r -> r.getDate().toLocalDate().equals(checkInDate.toLocalDate())
                        && r.getWorkType() == WorkRecord.WorkType.CHECK_OUT)
                .findFirst()
                .orElse(new WorkRecord(employee, checkInDate.plusHours(8), WorkRecord.WorkType.CHECK_OUT)) // 8시간 후 퇴근
                                                                                                           // 가정
                .getDate();
    }

    /* 공제 사항 계산 */
    private double calculateDeductions(double grossPay) {
        double taxRate = 0.15; /* 세금율 15% */
        return grossPay * taxRate;
    }
}