package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Region;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface RegionRepository extends CrudRepository<Region, String>{
    
}
