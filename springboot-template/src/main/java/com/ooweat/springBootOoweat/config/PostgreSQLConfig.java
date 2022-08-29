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
@MapperScan(value = "com.ooweat.springBootOoweat.mappers.postgresql", sqlSessionFactoryRef = "postgreSqlSessionFactory")
public class PostgreSQLConfig {

    @Bean(name = "postgresql")
    @ConfigurationProperties(prefix = "spring.postgresql.datasource")
    public DataSource postgreSqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgreSqlSessionFactory")
    public SqlSessionFactory postgreSqlSessionFactory(@Qualifier("postgresql") DataSource postgresDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(postgresDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.ooweat.springBootOoweat.model");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/postgresql/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name = "postgreSqlSessionTemplate")
    public SqlSessionTemplate postgreSqlSessionTemplate(@Qualifier("postgreSqlSessionFactory") SqlSessionFactory postgreSqlSessionFactory) {
        return new SqlSessionTemplate(postgreSqlSessionFactory);
    }
}
