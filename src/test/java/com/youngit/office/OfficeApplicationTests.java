package com.youngit.office;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OfficeApplicationTests {

    @Test
    void contextLoads() {
    }

   @BeforeAll
   static void beforeAll() //전체 테스트 시작전 1회 실행 >> static으로 선언 >>db연결, 테스트환경 초기화할때 사용
   {
       System.out.println("Before all test cases!!!!");
   }

   @BeforeEach
   public void beforeEach() //테스트 케이스 시작하기 전마다 실행 test()에서 사용객체 초기화, 테스트 필요한값 미리 세팅
   {
         System.out.println("Before each test case");
   }

   @Test
   public void Test1()
   {
       System.out.println("Test1");
   }

   @Test
   public void Test2()
   {
       System.out.println("Test2");
   }

   @AfterAll
   static void afterAll() //전체 테스트 마치고 종료전 1회 실행 >> static으로 선언 >>db연결 종료할 때, 공통적 사용 자원 해제할 때
   {
       System.out.println("After all test cases");
   }

   @AfterEach
   public void afterEach() //테스트 케이스 종료하기 전마다 실행 >>테스트 이후 특정 데이터 삭제해야하는 경우
   {
       System.out.println("After each test case");
   }

}
