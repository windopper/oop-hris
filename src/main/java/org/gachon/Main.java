package org.gachon;

import org.gachon.employee.Employee;
import org.gachon.employee.EmployeeRepository;
import org.gachon.employee.NaiveEmployeeRepository;

public class Main {
    public static void main(String[] args) {
        // 예시 직원 생성
        Employee employee = Employee.builder()
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
        // EmployeeRepository 인스턴스 생성
        EmployeeRepository repository = new NaiveEmployeeRepository();

        // 직원 저장
        repository.save(employee);
    }
}
