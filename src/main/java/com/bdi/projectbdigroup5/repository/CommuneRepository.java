package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Commune;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuneRepository extends CrudRepository<Commune, String> , PagingAndSortingRepository<Commune, String> {
    @Query("SELECT c.nom FROM Commune c")
    Page<String> findAllCommuneNom(Pageable pageable);
}
