package com.DistribuidoraJD.persistence.repositories;

import com.DistribuidoraJD.model.Sale;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale,Long> {

    Optional<Sale> findById(long code);

}
