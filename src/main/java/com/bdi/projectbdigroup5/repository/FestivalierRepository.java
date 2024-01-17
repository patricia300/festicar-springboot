package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Festivalier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FestivalierRepository extends CrudRepository <Festivalier, String> {

}
