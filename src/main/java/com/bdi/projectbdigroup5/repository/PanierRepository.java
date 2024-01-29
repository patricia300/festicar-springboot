package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.Panier;
import com.bdi.projectbdigroup5.model.StatutPanier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository extends CrudRepository<Panier,Long> {
    List<Panier> findAllByFestivalierEmail(String email);

    Panier findFirstByFestivalierEmailAndStatut(String email, StatutPanier statutPanier);
}
