package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreCovoiturageRepository extends CrudRepository<OffreCovoiturage, Long> , PagingAndSortingRepository<OffreCovoiturage, Long> {
    Page<OffreCovoiturage> findAll(Pageable pageable);
}
