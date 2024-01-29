package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import com.bdi.projectbdigroup5.model.OffreCovoiturage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointPassageCovoiturageRepository extends CrudRepository<PointPassageCovoiturage, Long>, PagingAndSortingRepository<PointPassageCovoiturage, Long> {

    Page<PointPassageCovoiturage> findAll(Pageable pageable);

    @Query("SELECT p FROM PointPassageCovoiturage p, OffreCovoiturage o where p.offreCovoiturage = o and o.nombrePlaces >= ?1")
    Page<PointPassageCovoiturage> findPlaceGreaterthan(int nombrePlace, Pageable pageable);
}
