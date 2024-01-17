package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Covoitureur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovoitureurRepository extends CrudRepository<Covoitureur, String> {
}
