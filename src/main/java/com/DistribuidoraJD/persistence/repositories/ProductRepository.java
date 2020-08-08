package com.DistribuidoraJD.persistence.repositories;

import com.DistribuidoraJD.model.ProductC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductC,Long> {

    Optional<ProductC> findByCode(long code);

    Optional<ProductC> findByName(String name);

    @Query("SELECT p.name FROM ProductC p ")
    List<String> getAllNames();

    boolean existsByCode(long code);

    void deleteByCode(long code);

    @Query("select p from ProductC p where p.name like %:name% or p.code = :code ")
    Page<ProductC> findByNameOrCode(String name, Long code, Pageable page);
}
