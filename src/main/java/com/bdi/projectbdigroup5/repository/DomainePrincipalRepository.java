package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.DomainePrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface DomainePrincipalRepository extends CrudRepository<DomainePrincipal, String>{
    
}
