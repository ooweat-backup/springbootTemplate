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
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.ooweat.springBootOoweat.mappers.maria", sqlSessionFactoryRef = "mariaSqlSessionFactory")
public class MariaDBConfig {

    @Primary
    @Bean(name = "maria")
    @ConfigurationProperties(prefix = "spring.maria.datasource")
    public DataSource mariaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mariaSqlSessionFactory")
    public SqlSessionFactory mariaSqlSessionFactory(@Qualifier("maria") DataSource mariaDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mariaDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.ooweat.springBootOoweat.model");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/maria/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "mariaSessionTemplate")
    public SqlSessionTemplate mariaSqlSessionTemplate(@Qualifier("mariaSqlSessionFactory") SqlSessionFactory mariaSqlSessionFactory) {
        return new SqlSessionTemplate(mariaSqlSessionFactory);
    }
}
