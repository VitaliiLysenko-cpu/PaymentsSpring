package com.lysenko.Payments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class PaymentsApplication {

	private static final Logger log = LoggerFactory.getLogger(PaymentsApplication.class);

	public static void main(String[] args) {
		log.debug("Starting application ...");
		SpringApplication.run(PaymentsApplication.class, args);
	}
}


