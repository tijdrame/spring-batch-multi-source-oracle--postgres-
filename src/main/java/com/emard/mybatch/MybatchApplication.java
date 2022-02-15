package com.emard.mybatch;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
@EnableBatchProcessing
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, DataSourceAutoConfiguration.class })
public class MybatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatchApplication.class, args);
	}

	@Bean
	BatchConfigurer configurer(@Qualifier("sysTrackDataSource") DataSource sysTrackDataSource) {
		return new DefaultBatchConfigurer(sysTrackDataSource);
	}

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setUsername("testdevboa1@gmail.com");
		javaMailSenderImpl.setPassword("P@sser0123");
		javaMailSenderImpl.setProtocol("smtp");
		javaMailSenderImpl.setPort(587);

		return javaMailSenderImpl;
	}

	@Bean
	public SimpleMailMessage templateMessage() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo("tijdrame@gmail.com");
		mailMessage.setSubject("Job Status");
		mailMessage.setFrom("testdevboa1@gmail.com");
		return mailMessage;
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

	//@Bean
	

}
