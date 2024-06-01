package org.gachon;

import org.gachon.employee.Employee;
import org.gachon.employee.EmployeeRepository;
import org.gachon.employee.NaiveEmployeeRepository;

public class Main {
    public static void main(String[] args) {
        // 예시 직원 생성
        Employee employee = Employee.builder()
                .name("김가천")
                .codeNumber(123456)
                .phoneNumber("000-0000-0000")
                .address("경기도 성남시 수정구 성남대로 1342")
                .email("gc@gachon.ac.kr")
                .hourlyRate(15000)
                .department("개발 1팀")
                .career("(주)성남컴퍼니 개발팀 대리 2015 - 2019 (주)가천컴퍼니 소프트웨어 개발팀 과장 2019 - 2023")
                .yearOfEmployment(2024)
                .marriage(false)
                .school("가천대학교")
                .militaryService(true)
                .rewardPunishment("기록 없음")
                .overWorkmin(0)
                .vacation(17)
                .certificate("자격증1 930점, 자격증2, 자격증3")
                .build();
        // EmployeeRepository 인스턴스 생성
        EmployeeRepository repository = NaiveEmployeeRepository.getInstance();

        // 직원 저장
        repository.save(employee);

    }
}
