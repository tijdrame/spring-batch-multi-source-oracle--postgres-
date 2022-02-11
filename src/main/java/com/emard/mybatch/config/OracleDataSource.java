package com.emard.mybatch.config;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories(
    basePackages = "com.emard.mybatch.modelOracle", 
    entityManagerFactoryRef = "oracleSysTrackEntityManager", 
    transactionManagerRef = "oracleSysTrackTransactionManager"
)
@EnableTransactionManagement
public class OracleDataSource {

    @Autowired
    private Environment env;

    /*@Autowired
    LocalContainerEntityManagerFactoryBean em;

    @Autowired
    JpaTransactionManager transactionManager;

    @Autowired   
    DriverManagerDataSource dataSource; */
    
    @Bean(name = "oracleSysTrackEntityManager")
    public LocalContainerEntityManagerFactoryBean oracleSysTrackEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(oracleSysTrackDataSource());
        em.setPackagesToScan(
          new String[] { "com.emard.mybatch.modelOracle" });

        HibernateJpaVendorAdapter vendorAdapter
          = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
          env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect","org.hibernate.dialect.Oracle12cDialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "oracleSysTrackDataSource")
    @Qualifier("oracleSysTrackDataSource")
    public DataSource oracleSysTrackDataSource() {
 
        DriverManagerDataSource dataSource
          = new DriverManagerDataSource();
        dataSource.setDriverClassName(
          env.getProperty("oracle.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("oracle.datasource.url"));
        dataSource.setUsername(env.getProperty("oracle.datasource.username"));
        dataSource.setPassword(env.getProperty("oracle.datasource.password"));

        return dataSource;
    }

    @Bean(name = "oracleSysTrackTransactionManager")
    @Resource
    public PlatformTransactionManager oracleSysTrackTransactionManager() {
 
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
            oracleSysTrackEntityManager().getObject());
        return transactionManager;
    }
}
