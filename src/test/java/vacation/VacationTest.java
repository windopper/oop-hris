package vacation;

import java.util.Date;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.gachon.employee.Employee;
import org.gachon.vacation.Vacation;
import org.gachon.vacation.VacationException;
import org.gachon.vacation.VacationService;
import org.junit.Before;
import org.junit.Test;

public class VacationTest {
    private VacationService vacationManager;
    private Employee employee;

    @Before
    public void setUp() {
        vacationManager = VacationService.getInstance();

        employee = Employee.builder()
                .name("Kim gachon")
                .phoneNumber("000-0000-0000")
                .email("gc@gachon.ac.kr")
                .salary(3000)
                .department("Dev1")
                .career("None")
                .school("Gachon University")
                .militaryService(true)
                .rewardPunishment("No records")
                .overWorkmin(0)
                .vacation(17)
                .build();
    }

    @Test
    public void testRegisterVacation() throws VacationException {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 5, 29);
        Date startDate = cal.getTime();

        cal.set(2024, 6, 1);
        Date endDate = cal.getTime();

        vacationManager.registerVacation(new Vacation(employee, startDate, endDate, ""));

        assertEquals(vacationManager.findVacations(employee).size(), 1);
    }

    @Test
    public void testRegisterVacationWithShortageException() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 5, 29);
        Date startDate = cal.getTime();

        cal.set(2025, 6, 1);
        Date endDate = cal.getTime();

        try {
            vacationManager.registerVacation(new Vacation(employee, startDate, endDate, ""));
        } catch (VacationException e) {
            assertEquals(e.getMessage(), VacationException.VACATION_SHORTAGE);
        }
    }

    @Test
    public void testRegisterVacationWithDuplicateException() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 5, 29);
        Date startDate = cal.getTime();

        cal.set(2024, 6, 1);
        Date endDate = cal.getTime();

        try {
            vacationManager.registerVacation(new Vacation(employee, startDate, endDate, ""));
            vacationManager.registerVacation(new Vacation(employee, startDate, endDate, ""));
        } catch (VacationException e) {
            assertEquals(e.getMessage(), VacationException.VACATION_OVERLAP);
        }
    }

    @Test
    public void testApproveVacation() throws VacationException {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 5, 29);
        Date startDate = cal.getTime();

        cal.set(2024, 6, 1);
        Date endDate = cal.getTime();

        Vacation vacation = new Vacation(employee, startDate, endDate, "");
        vacationManager.registerVacation(vacation);

        vacationManager.approveVacation(vacation);

        assertEquals(vacation.isApproved(), true);
    }

    @Test
    public void testRejectVacation() throws VacationException {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 5, 29);
        Date startDate = cal.getTime();

        cal.set(2024, 6, 1);
        Date endDate = cal.getTime();

        Vacation vacation = new Vacation(employee, startDate, endDate, "");
        vacationManager.registerVacation(vacation);

        vacationManager.rejectVacation(vacation);

        assertEquals(vacation.isApproved(), false);
    }

    @Test
    public void testProcessedVacation() throws VacationException {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 5, 29);
        Date startDate = cal.getTime();

        cal.set(2024, 6, 1);
        Date endDate = cal.getTime();

        Vacation vacation = new Vacation(employee, startDate, endDate, "");
        vacationManager.registerVacation(vacation);

        vacationManager.approveVacation(vacation);

        try {
            vacationManager.approveVacation(vacation);
        } catch (VacationException e) {
            assertEquals(e.getMessage(), VacationException.VACATION_APPROVED);
        }

        try {
            vacationManager.rejectVacation(vacation);
        } catch (VacationException e) {
            assertEquals(e.getMessage(), VacationException.VACATION_APPROVED);
        }
    }
}
