package com.DistribuidoraJD.persistence.repositories;

import com.DistribuidoraJD.model.ProductCopy;
import com.DistribuidoraJD.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SaleRepository extends JpaRepository<Sale,Long> {

    Optional<Sale> findById(long code);

}
