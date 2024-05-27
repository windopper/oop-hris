package org.gachon.employee;

import java.util.List;

public interface EmployeeRepository {
    public void save(Employee employee);

    public void delete(Employee employee);

    public void update(Employee employee);

    public List<Employee> findByName(String name);

    public List<Employee> findByPhoneNumber(String phoneNumber);

    public List<Employee> findByEmail(String email);

    public List<Employee> findByDepartment(String department);

    public List<Employee> findBySchool(String school);

    public List<Employee> findByMilitaryService(boolean militaryService);

    public List<Employee> findByRewardPunishment(String rewardPunishment);

    public List<Employee> findByCareer(String career);

    public List<Employee> findBySalary(int salary);
}
