package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Organisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisateurRepository extends CrudRepository<Organisateur, String> {
}
