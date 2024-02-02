package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Organisateur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisateurRepository extends CrudRepository<Organisateur, String>, PagingAndSortingRepository<Organisateur, String> {
    Page<Organisateur> findAll(Pageable pageable);
}
