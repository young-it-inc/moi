package com.youngit.office.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration //240911 안적혀있으면 의존성주입 실패 예외 발생. (UnsatisfiedDependencyException)
//@RequiredArgsConstructor
@MapperScan("com.youngit.office.api.*.mapper") //Spring Boot 애플리케이션에서 sqlSessionTemplate 빈을 찾지 못해 발생하는 문제 해결
public class MybatisConfig {

    @Autowired
    private DataSource dataSource;

    public void checkDataSource() {
        if(dataSource == null) {
            System.out.println("dataSource is null");
        } else {
            System.out.println("dataSource is available : " + dataSource);
        }
    }
    @Bean //myBatis의 {@link org.apache.ibatis.session.SqlSessionFactory}을 생성하는 팩토리빈을 등록
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        System.out.println("init 3: MybatisConfig의 sqlSessionFactory(), dataSource: " + dataSource);
        checkDataSource();
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
      //  sessionFactory.setConfigLocation(new ClassPathResource("mybatis/configuration.xml"));
        return sessionFactory;
    }
    @Bean //MyBatis의 {@link org.apache.ibatis.session.SqlSession} 빈을 등록, SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        System.out.println("init 4: MybatisConfig의 sqlSessionTemplate()" + sqlSessionFactory);
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
