package com.DistribuidoraJD.persistence.repositories;

import com.DistribuidoraJD.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    public Optional<Product> findByCode(long code);

    public boolean existsByCode(long code);

    public void deleteByCode(long code);
}
