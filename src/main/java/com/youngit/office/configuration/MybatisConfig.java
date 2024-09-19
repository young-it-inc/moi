package com.youngit.office.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;


//@Configuration : 어느테이션만 주석처리해도 여기 타지않음
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        System.out.println("MybatisConfig의 sqlSessionFactory()의 dataSource: " + dataSource);
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        System.out.println("MybatisConfig의 sqlSessionTemplate()");
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
