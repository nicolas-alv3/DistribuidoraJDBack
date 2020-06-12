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

	@Bean("threadPoolTaskExecutor")
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(20);
		executor.initialize();
		return executor;
	}

	@Bean
	public CommandLineRunner demo(ProductRepository productRepository) {
		return (args) -> {
			Product donSatur = new Product("Don Satur");
			productRepository.save(donSatur);
		};
	}
}
