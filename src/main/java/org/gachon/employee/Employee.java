package org.gachon.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 직원 정보를 담는 클래스
 */
@Getter
@Setter
@Builder
public class Employee {
    @NonNull
    private String name;
    // 직원 개인 식별 번호
    private int codeNumber;
    private String phoneNumber;
    private String address;
    private String email;
    private int salary;
    private String department;
    private String career;
    // 입사 연도
    private int yearOfEmployment;
    // 결혼 여부
    private boolean marriage;
    // 출신 학교
    private String school;
    // 병역 여부
    private boolean militaryService;
    // 상벌 현황
    private String rewardPunishment;
    // 초과근무시간(분)
    private int overWorkmin;
    // 연차
    private int vacation;
    // 자격증
    private String certificate;
}
