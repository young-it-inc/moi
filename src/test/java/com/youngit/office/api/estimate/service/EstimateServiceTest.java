package com.youngit.office.api.estimate.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EstimateServiceTest {
/*
모든 test는 순서보장x  >> 순서 의존적으로 설계하면 x
테스트 코드 먼저 구현해서 원하는 틀부터 만들고 개발하는 것 : 테스트주도개발(TDD) <-> 구현->테스트
 */


    @Autowired
    private EstimateService estimateService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        // 메소드가 끝날 때마다 실행 (콜백메소드)
        System.out.println("Test End");
    }

    @Test
    void getOrSearchListEstimate() {
        //given

        //when

        //then

    }

    @Test
    void countGetOrSearchListEstimate() {

    }

    @Test
    void getOneEstimate() {
    }

    @Test
    public void getNewEstimateUniqNo() {

    }

    @Test
    void registerEstimate() {
    }

    @Test
    public void calculateEstimateAmount()
    {

    }

    @Test
    void generateEstimateExcel() {
    }

    @Test
    void generateExcelEstimateList() {
    }

    @Test
    void updateEstimate() {
    }

    @Test
    void deleteEstimate() {
    }

    @Test
    void registerEstimateContract() {
    }

    @Test
    void sendEmailEstimate() {
    }
}