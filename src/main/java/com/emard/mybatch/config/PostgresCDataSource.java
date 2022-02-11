package com.emard.mybatch.config;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories(
    basePackages = "com.emard.mybatch.model", 
    entityManagerFactoryRef = "sysTrackEntityManager", 
    transactionManagerRef = "sysTrackTransactionManager"
)
public class PostgresCDataSource {

    @Autowired
    private Environment env;

    /*@Autowired
    LocalContainerEntityManagerFactoryBean em;

    @Autowired
    JpaTransactionManager transactionManager;
    @Autowired   
    DriverManagerDataSource dataSource; */

    
    @Bean(name = "sysTrackEntityManager")
    @Primary
    @Qualifier("sysTrackEntityManager")
    public LocalContainerEntityManagerFactoryBean sysTrackEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(sysTrackDataSource());
        em.setPackagesToScan(
          new String[] { "com.emard.mybatch.model" });

        HibernateJpaVendorAdapter vendorAdapter
          = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
          env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
          env.getProperty("spring.datasource.jdbc.driverClassName"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean(name = "sysTrackDataSource")
    @Qualifier("sysTrackDataSource")
    @BatchDataSource
    public DataSource sysTrackDataSource() {
 
        DriverManagerDataSource dataSource2
          = new DriverManagerDataSource();
        dataSource2.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource2.setUrl(env.getProperty("spring.datasource.url"));
        dataSource2.setUsername(env.getProperty("spring.datasource.username"));
        dataSource2.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource2;
    }

    @Primary
    @Bean(name = "sysTrackTransactionManager")
    @Qualifier("sysTrackTransactionManager")
    @Resource
    public PlatformTransactionManager sysTrackTransactionManager() {
 
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
            sysTrackEntityManager().getObject());
        return transactionManager;
    }
}
