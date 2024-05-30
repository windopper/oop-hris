package vacation;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.gachon.employee.Employee;
import org.gachon.workrecord.WorkRecordService;
import org.gachon.workrecord.WorkRecord;

import org.junit.Before;
import org.junit.Test;

public class WorkRecordTest {
    private WorkRecordService workRecordService;
    private Employee employee = Employee.builder().name("test").build();

    @Before
    public void setUp() {
        workRecordService = WorkRecordService.getInstance();
    }

    @Test
    public void testCheckIn() {
        workRecordService.checkIn(employee);

        List<WorkRecord> workRecords = workRecordService.getWorkRecords(employee);
        assertEquals(1, workRecords.size());
        assertEquals(WorkRecord.WorkType.CHECK_IN, workRecords.get(0).getWorkType());
    }

    @Test
    public void testCheckOut() {
        workRecordService.checkOut(employee);

        List<WorkRecord> workRecords = workRecordService.getWorkRecords(employee);
        assertEquals(1, workRecords.size());
        assertEquals(WorkRecord.WorkType.CHECK_OUT, workRecords.get(0).getWorkType());
    }

    @Test
    public void testOverTime() {
        workRecordService.overTime(employee);

        List<WorkRecord> workRecords = workRecordService.getWorkRecords(employee);
        assertEquals(1, workRecords.size());
        assertEquals(WorkRecord.WorkType.OVERTIME, workRecords.get(0).getWorkType());
    }

    @Test
    public void testDeleteWorkRecord() {
        workRecordService.checkIn(employee);
        workRecordService.checkOut(employee);

        List<WorkRecord> workRecords = workRecordService.getWorkRecords(employee);
        assertEquals(2, workRecords.size());

        workRecordService.deleteWorkRecord(workRecords.get(0));

        workRecords = workRecordService.getWorkRecords(employee);
        assertEquals(1, workRecords.size());
        assertEquals(WorkRecord.WorkType.CHECK_OUT, workRecords.get(0).getWorkType());
    }
}
