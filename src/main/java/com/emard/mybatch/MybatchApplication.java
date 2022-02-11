package com.emard.mybatch;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class MybatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatchApplication.class, args);
	}

	@Bean
    BatchConfigurer configurer(@Qualifier("sysTrackDataSource") DataSource sysTrackDataSource){
    	return new DefaultBatchConfigurer(sysTrackDataSource);
    }
	/*
	 * @Bean
	 * DriverManagerDataSource dataSource(){
	 * return new DriverManagerDataSource();
	 * }
	 * 
	 * @Bean
	 * LocalContainerEntityManagerFactoryBean em() {
	 * return new LocalContainerEntityManagerFactoryBean();
	 * }
	 * 
	 * @Bean
	 * JpaTransactionManager transactionManager(){
	 * return new JpaTransactionManager();
	 * }
	 * 
	 * @Bean
	 * LocalContainerEntityManagerFactoryBean em() {
	 * return new LocalContainerEntityManagerFactoryBean();
	 * }
	 */

}
