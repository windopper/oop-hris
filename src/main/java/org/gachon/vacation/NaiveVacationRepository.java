package org.gachon.vacation;

import java.util.List;
import java.util.ArrayList;

import org.gachon.employee.Employee;

public class NaiveVacationRepository implements VacationRepository {
    private List<Vacation> vacations = new ArrayList<>();

    @Override
    public void save(Vacation vacation) {
        vacations.add(vacation);
    }

    @Override
    public void delete(Vacation vacation) {
        vacations.remove(vacation);
    }

    @Override
    public void update(Vacation vacation) {
        if (vacations.contains(vacation)) {
            vacations.remove(vacation);
            vacations.add(vacation);
        }
    }

    @Override
    public List<Vacation> findByEmployee(Employee employee) {
        return vacations.stream().filter(v -> v.getEmployee().equals(employee)).toList();
    }

}
