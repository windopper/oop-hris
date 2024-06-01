package vacation;

import java.time.LocalDateTime;

import org.gachon.employee.Employee;
import org.gachon.payroll.PayrollService;
import org.gachon.vacation.NaiveVacationRepository;
import org.gachon.vacation.VacationRepository;
import org.gachon.vacation.VacationService;
import org.gachon.workrecord.NaiveWorkRecordRepository;
import org.gachon.workrecord.WorkRecord;
import org.gachon.workrecord.WorkRecordRepository;
import org.gachon.workrecord.WorkRecordService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PayrollTest {

    private PayrollService payrollService;
    private VacationRepository vacationRepository;
    private WorkRecordRepository workRecordRepository;

    @Before
    public void setUp() {
        payrollService = PayrollService.getInstance();
        workRecordRepository = NaiveWorkRecordRepository.getInstance();
    }

    @Test
    public void testCalculatePayroll() {
        // Given
        Employee employee = Employee.builder().name("test").hourlyRate(15000).build();
        workRecordRepository
                .save(new WorkRecord(employee, LocalDateTime.of(2024, 6, 1, 8, 0, 0), WorkRecord.WorkType.CHECK_IN));
        workRecordRepository
                .save(new WorkRecord(employee, LocalDateTime.of(2024, 6, 1, 16, 0, 0), WorkRecord.WorkType.CHECK_OUT));

        double payroll = payrollService.calculatePay(employee,
                LocalDateTime.of(2024, 5, 28, 8, 0, 0),
                LocalDateTime.of(2024, 6, 1, 16, 0, 0));

        // Then
        assertEquals(102000, payroll, 0.1);
    }

    @Test
    public void testMultiplePayroll() {
        // Given
        Employee employee = Employee.builder().name("test").hourlyRate(15000).build();
        workRecordRepository
                .save(new WorkRecord(employee, LocalDateTime.of(2024, 6, 1, 8, 0, 0), WorkRecord.WorkType.CHECK_IN));
        workRecordRepository
                .save(new WorkRecord(employee, LocalDateTime.of(2024, 6, 1, 16, 0, 0), WorkRecord.WorkType.CHECK_OUT));
        workRecordRepository
                .save(new WorkRecord(employee, LocalDateTime.of(2024, 6, 2, 8, 0, 0), WorkRecord.WorkType.CHECK_IN));
        workRecordRepository
                .save(new WorkRecord(employee, LocalDateTime.of(2024, 6, 2, 16, 0, 0), WorkRecord.WorkType.CHECK_OUT));

        double payroll = payrollService.calculatePay(employee,
                LocalDateTime.of(2024, 6, 1, 8, 0, 0),
                LocalDateTime.of(2024, 6, 2, 16, 0, 0));

        // Then
        assertEquals(204000, payroll, 0.1);
    }
}
