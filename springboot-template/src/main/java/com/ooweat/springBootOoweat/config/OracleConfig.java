package com.ooweat.springBootOoweat.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.ooweat.springBootOoweat.mappers.oracle", sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class OracleConfig {

    @Bean(name = "oracle")
    @ConfigurationProperties(prefix = "spring.oracle.datasource")
    public DataSource oracleDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleSqlSessionFactory")
    public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracle") DataSource oracleDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(oracleDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.ooweat.springBootOoweat.model");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/oracle/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "oracleSessionTemplate")
    public SqlSessionTemplate oracleSqlSessionTemplate(@Qualifier("oracleSqlSessionFactory") SqlSessionFactory oracleSqlSessionFactory){
        return new SqlSessionTemplate(oracleSqlSessionFactory);
    }
}
