package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Festival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

@Repository
public interface FestivalRepository extends CrudRepository<Festival, Long>, PagingAndSortingRepository<Festival, Long> {

    Page<Festival> findAll(Pageable pageable);

    List<Festival> findAllByCommuneCodeInsee(String commune);

    List<Festival> findAllByDateDebut(Date dateDebut);

    List<Festival> findAllBySousDomaineDomainePrincipalNomContainingIgnoreCaseOrSousDomaineNomContainingIgnoreCase(String sousDomaine_domainePrincipal_nom, String sousDomaine_nom);

    Page<Festival> findAllByDateDebutAfterAndCommuneCodeInseeAndSousDomaineNomContainingOrSousDomaineDomainePrincipalNomContaining(
            Date dateDebut, String communeCodeInsee, String sousDomaineNom, String domainePrincipal, Pageable pageable);

    List<Festival> findAllByNomContainingIgnoreCase(String nom);
}
