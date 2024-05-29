package org.gachon.vacation;

import java.util.List;

import org.gachon.employee.Employee;

public interface VacationRepository {
    public void save(Vacation vacation);

    public void delete(Vacation vacation);

    public void update(Vacation vacation);

    public List<Vacation> findByEmployee(Employee employee);
}
