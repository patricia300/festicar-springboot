package com.bdi.projectbdigroup5.service.ServiceInterface;

import com.bdi.projectbdigroup5.model.Panier;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;



public interface PanierServiceInterface {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Panier updatePanierStatusToPayed(Long id);
    
}
