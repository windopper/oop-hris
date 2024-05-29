package org.gachon.employee;

import java.util.ArrayList;
import java.util.List;

public class NaiveEmployeeRepository implements EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    @Override
    public void save(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void delete(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public void update(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
            employees.add(employee);
        }
    }

    @Override
    public List<Employee> findByName(String name) {
        return employees.stream().filter(e -> e.getName().equals(name)).toList();
    }

    @Override
    public List<Employee> findByCodeNumber(int codeNumber) {
        return employees.stream().filter(e -> e.getCodeNumber() == codeNumber).toList();
    }

    @Override
    public List<Employee> findByPhoneNumber(String phoneNumber) {
        return employees.stream().filter(e -> e.getPhoneNumber().equals(phoneNumber)).toList();
    }

    @Override
    public List<Employee> findByEmail(String email) {
        return employees.stream().filter(e -> e.getEmail().equals(email)).toList();
    }

    @Override
    public List<Employee> findByDepartment(String department) {
        return employees.stream().filter(e -> e.getDepartment().equals(department)).toList();
    }

    @Override
    public List<Employee> findByYearOfEmployment(int yearOfEmployment) {
        return employees.stream().filter(e -> e.getYearOfEmployment() == yearOfEmployment).toList();
    }

    @Override
    public List<Employee> findByMarriage(boolean marriage) {
        return employees.stream().filter(e -> e.isMarriage() == marriage).toList();
    }

    @Override
    public List<Employee> findBySchool(String school) {
        return employees.stream().filter(e -> e.getSchool().equals(school)).toList();
    }

    @Override
    public List<Employee> findByMilitaryService(boolean militaryService) {
        return employees.stream().filter(e -> e.isMilitaryService() == militaryService).toList();
    }

    @Override
    public List<Employee> findByRewardPunishment(String rewardPunishment) {
        return employees.stream().filter(e -> e.getRewardPunishment().equals(rewardPunishment)).toList();
    }

    @Override
    public List<Employee> findByCareer(String career) {
        return employees.stream().filter(e -> e.getCareer().equals(career)).toList();
    }

    @Override
    public List<Employee> findBySalary(int salary) {
        return employees.stream().filter(e -> e.getSalary() == salary).toList();
    }

    @Override
    public List<Employee> findByCertificate(String certificate) {
        return employees.stream().filter(e -> e.getCertificate().equals(certificate)).toList();
    }

}
