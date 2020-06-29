package com.DistribuidoraJD.persistence.repositories;

import com.DistribuidoraJD.model.ProductC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductC,Long> {

    Optional<ProductC> findByCode(long code);

    boolean existsByCode(long code);

    void deleteByCode(long code);
}
