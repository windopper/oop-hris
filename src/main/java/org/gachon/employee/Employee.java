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
    private String phoneNumber;
    private String address;
    private String email;
    private int salary;
    private String department;
    private String career;
    // 출신 학교
    private String school;
    // 병역 여부
    private boolean militaryService;
    // 상벌 현황
    private String rewardPunishment;
}
