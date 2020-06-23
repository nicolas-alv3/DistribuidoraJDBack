package com.DistribuidoraJD.persistence.repositories;

import com.DistribuidoraJD.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
