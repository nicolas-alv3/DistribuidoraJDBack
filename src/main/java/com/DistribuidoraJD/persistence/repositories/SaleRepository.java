package com.DistribuidoraJD.persistence.repositories;

import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.model.Sale;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale,Long> {

    Optional<Sale> findById(long code);

    @Query("select s from Sale s where s.client.name like %:name% or s.details like %:name% or s.id = :code ")
    List<Sale> findByClientNameLike(@Param("name") String name,@Param("code") long code);

}
