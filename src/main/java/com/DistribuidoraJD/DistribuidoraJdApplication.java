package com.DistribuidoraJD;

import com.DistribuidoraJD.model.*;
import com.DistribuidoraJD.persistence.repositories.ProductRepository;
import com.DistribuidoraJD.persistence.repositories.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableAsync
@SpringBootApplication
public class DistribuidoraJdApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistribuidoraJdApplication.class, args);
	}

/*    @Bean
    public CommandLineRunner demo(ProductRepository productRepository, SaleRepository saleRepository) {
        return (args) -> {
            // Just to test with lot of products and sales
            *//*createDemoProducts(productRepository,10000);
            createDemoSales(saleRepository,1);*//*
        };
    }

    private void createDemoSales(SaleRepository saleRepository, int amount) {
        for (int i = 0; i < amount; i++) {
            Client nico = new Client("nico","luffer",340404);
            ProductCopy ds = new ProductC(1,"Don satur",10.0,4.0,10,30,400, ProductCategory.valueOf("GALLETITAS")).copy();
            SaleItem dsItem = new SaleItem(ds,1);
            List<SaleItem> itemList = new ArrayList<>();
            itemList.add(dsItem);
            saleRepository.save(new Sale(nico,itemList,"este es el de prueba"));
        }
    }

    private void createDemoProducts(ProductRepository productRepository,Integer amount) {
        for (int i = 1; i < amount; i++) {
            String name = String.valueOf(Math.random());
            ProductC donSatur = new ProductC(i,name,10.0,4.0,10,30,400, ProductCategory.valueOf("GALLETITAS"));
            productRepository.save(donSatur);
        }

    }*/
}
