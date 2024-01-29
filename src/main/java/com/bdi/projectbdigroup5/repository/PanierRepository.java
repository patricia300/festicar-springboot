package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Panier;
import com.bdi.projectbdigroup5.model.StatutPanier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository extends CrudRepository<Panier,Long> {
    Iterable<Panier> findAllByFestivalierEmail(String email);

    Panier findFirstByFestivalierEmailAndStatut(String email, StatutPanier statutPanier);
}
