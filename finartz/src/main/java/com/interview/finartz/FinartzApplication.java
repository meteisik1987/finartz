package com.interview.finartz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableJpaRepositories
@EnableJpaAuditing
@EnableTransactionManagement
@ComponentScan(basePackageClasses = FinartzApplication.class)
public class FinartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinartzApplication.class, args);
	}

}
