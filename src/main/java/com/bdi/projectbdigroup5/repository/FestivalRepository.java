package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Festival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

@Repository
public interface FestivalRepository extends CrudRepository<Festival, Long>, PagingAndSortingRepository<Festival, Long> {

    Page<Festival> findAll(Pageable pageable);

    Page<Festival> findAllByCommuneNom(String commune, Pageable pageable);

    Page<Festival> findAllByDateDebutOrDateFin(String dateDebut, String dateFin, Pageable pageable);

    Page<Festival> findAllByDateDebut(String dateDebut, Pageable pageable);

    Page<Festival> findAllByDateFin(String dateFin, Pageable pageable);

    Page<Festival> findAllByDateDebutAndCommuneCodeInseeAndSousDomaineNom(
            Date dateDebut, String communeCodeInsee, String sousDomaineNom, Pageable pageable);
}
