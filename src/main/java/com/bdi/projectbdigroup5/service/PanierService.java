package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.PanierRequestBodyDto;
import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.model.Panier;
import com.bdi.projectbdigroup5.repository.ArticleRepository;
import com.bdi.projectbdigroup5.repository.FestivalierRepository;
import com.bdi.projectbdigroup5.repository.PanierRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PanierService {
    private PanierRepository panierRepository;
    private FestivalierRepository festivalierRepository;
    private ArticleRepository articleRepository;

    public Iterable<Panier> getPanierByFestivalierEmail(String email) {
        return this.panierRepository.findAllByFestivalierEmail(email);
    }

    public Optional<Panier> savePanierFestivalier(PanierRequestBodyDto panierRequestBodyDto){


        Festivalier festivalier = festivalierRepository
                .findById(panierRequestBodyDto.getEmailFestivalier())
                .orElseThrow(() -> new RuntimeException("Festivalier non trouvé"));

        Panier panier = new Panier();
        panier.setFestivalier(festivalier);
        this.panierRepository.save(panier);


       List<Article> articles = panierRequestBodyDto.getIdArticles().stream()
                .map(id -> {
                     return articleRepository
                            .findById(id)
                            .orElseThrow(() -> new RuntimeException("Article $id non trouvé"));

                }).collect(Collectors.toList());

       this.articleRepository.saveAll(articles);
       return Optional.of(panier);
    }
}
