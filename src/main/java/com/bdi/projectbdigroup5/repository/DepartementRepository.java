package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Departement;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface DepartementRepository extends CrudRepository<Departement, String> { }
