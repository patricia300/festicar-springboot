package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.LieuCovoiturage;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface LieuCovoiturageRepository extends CrudRepository<LieuCovoiturage, Long>, PagingAndSortingRepository<LieuCovoiturage, Long>{
   
    Iterable<LieuCovoiturage> findByNom(String nom);

    Page<LieuCovoiturage> findAll(Pageable pageable);
}
