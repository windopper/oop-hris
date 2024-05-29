package org.gachon.vacation;

import java.util.Date;
import java.util.List;

import org.gachon.employee.Employee;

/**
 * 휴가 관리 클래스
 */
public class VacationService {
    private VacationRepository vacationRepository = new NaiveVacationRepository();
    private static VacationService instance;

    private VacationService() {
    }

    public static VacationService getInstance() {
        if (instance == null) {
            instance = new VacationService();
        }
        return instance;
    }

    public List<Vacation> findVacations(Employee employee) {
        return vacationRepository.findByEmployee(employee);
    }

    /**
     * 휴가 등록
     * 
     * @param vacation
     * @throws VacationException
     */
    public void registerVacation(Vacation vacation) throws VacationException {
        Employee employee = vacation.getEmployee();

        int vacationDays = getVacationDays(vacation);

        checkRemainingVacationDays(employee, vacationDays);

        List<Vacation> filteredVacations = findVacations(employee);

        for (Vacation origin : filteredVacations) {
            checkIsDuplicate(vacation, origin);
        }

        vacationRepository.save(vacation);
        employee.setVacation(employee.getVacation() - vacationDays);
    }

    /**
     * 남은 휴가 일수 확인
     * 
     * @param employee
     * @param days
     * @return
     * @throws VacationException
     */
    private boolean checkRemainingVacationDays(Employee employee, int days) throws VacationException {
        int remainVacationDays = employee.getVacation();

        if (days > remainVacationDays)
            throw new VacationException(VacationException.VACATION_SHORTAGE);
        return true;
    }

    /**
     * 중복 휴가 확인
     *
     * @param newVacation
     * @param originVacation
     * @throws VacationException
     */
    private void checkIsDuplicate(Vacation newVacation, Vacation originVacation) throws VacationException {
        Date newStartDate = newVacation.getStartDate();
        Date newEndDate = newVacation.getEndDate();

        Date startDate = originVacation.getStartDate();
        Date endDate = originVacation.getEndDate();

        if ((newStartDate.compareTo(startDate) >= 0 && newStartDate.compareTo(endDate) <= 0)
                || (newEndDate.compareTo(startDate) >= 0 && newEndDate.compareTo(endDate) <= 0))
            throw new VacationException(VacationException.VACATION_OVERLAP);
    }

    /**
     * 휴가 승인
     * 
     * @param vacation
     */
    public void approveVacation(Vacation vacation) throws VacationException {
        // 휴가 승인
        checkProcessedVacation(vacation);
        vacation.setApproved(true);
        vacationRepository.update(vacation);
    }

    /**
     * 휴가 거절
     * 
     * @param vacation
     */
    public void rejectVacation(Vacation vacation) throws VacationException {
        checkProcessedVacation(vacation);

        vacation.setCanceled(true);
        vacationRepository.update(vacation);

        int vacationDays = getVacationDays(vacation);

        vacation.getEmployee().setVacation(vacation.getEmployee().getVacation() + vacationDays);
    }

    /**
     * 이미 처리된 휴가인지 확인
     * 
     * @param vacation
     * @throws VacationException
     */
    private void checkProcessedVacation(Vacation vacation) throws VacationException {
        if (vacation.isApproved())
            throw new VacationException(VacationException.VACATION_APPROVED);
        if (vacation.isCanceled())
            throw new VacationException(VacationException.VACATION_CANCELED);
    }

    private int getVacationDays(Vacation vacation) {
        Date startDate = vacation.getStartDate();
        Date endDate = vacation.getEndDate();

        long diff = endDate.getTime() - startDate.getTime();
        return (int) (diff / (24 * 60 * 60 * 1000));
    }
}
