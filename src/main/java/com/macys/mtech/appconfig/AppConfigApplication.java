package com.macys.mtech.appconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * @author Sanjiv Pansari
 *
 */

@EnableJpaRepositories(
		repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)

@Configuration
@SpringBootApplication
@EntityScan(basePackages = {"com.macys.mtech.appconfig"})
public class AppConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppConfigApplication.class, args);
	}
    
}
