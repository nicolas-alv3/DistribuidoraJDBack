package com.DistribuidoraJD;

import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.persistence.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@SpringBootApplication
public class DistribuidoraJdApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistribuidoraJdApplication.class, args);
	}
}
