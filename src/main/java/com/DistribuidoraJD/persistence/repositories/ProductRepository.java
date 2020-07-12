package com.DistribuidoraJD.persistence.repositories;

import com.DistribuidoraJD.model.ProductC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.FutureOrPresent;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductC,Long> {

    Optional<ProductC> findByCode(long code);

    Optional<ProductC> findByName(String name);

    @Query("SELECT p.name FROM ProductC p WHERE p.stock > 0")
    List<String> getAllNames();

    boolean existsByCode(long code);

    void deleteByCode(long code);
}
