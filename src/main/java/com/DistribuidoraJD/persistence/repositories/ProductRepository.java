package com.DistribuidoraJD.persistence.repositories;

import com.DistribuidoraJD.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    public Product getByName(String name);
}
