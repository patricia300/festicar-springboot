package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.dto.ErreurPaiementPanierResponseDto;
import com.bdi.projectbdigroup5.exception.NombrePassFestivalInsuffisantException;
import com.bdi.projectbdigroup5.exception.NombrePlaceCovoiturageInsuffisantException;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.exception.QuantiteZeroException;
import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.ErreurPaiementClass;
import com.bdi.projectbdigroup5.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ArticleController {
    private ArticleService articleService;
    @PostMapping("/article")
    public ResponseEntity<? extends Object> createArticle(@RequestBody ArticleRequestBodyDto requestBodyDto){
        try {
            return ResponseEntity.ok(this.articleService.saveArticle(requestBodyDto));
        }
        catch (NotFoundException notFoundException) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage()))
                    .build();
        }
        catch (NombrePassFestivalInsuffisantException nombrePassFestivalInsuffisantException) {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    nombrePassFestivalInsuffisantException.getIdFestival(),
                    nombrePassFestivalInsuffisantException.getNbPassDisponible(),
                    ErreurPaiementClass.FESTIVAL
            ));
        }
        catch (NombrePlaceCovoiturageInsuffisantException nombrePlaceCovoiturageInsuffisantException)
        {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    nombrePlaceCovoiturageInsuffisantException.getIdOffreCovoiturage(),
                    nombrePlaceCovoiturageInsuffisantException.getNbPlaceDisponible(),
                    ErreurPaiementClass.OFFRE_COVOITURAGE
            ));
        }
        catch (QuantiteZeroException quantiteZeroException) {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    quantiteZeroException.getId(),
                    0,
                    quantiteZeroException.getClassType()
            ));
        }
    }

    @PostMapping("/articles")
    public ResponseEntity<? extends Object> createAllArticles(@RequestBody List<ArticleRequestBodyDto> requestBodyDtos){
        try {
            return ResponseEntity.ok(this.articleService.saveAllArticle(requestBodyDtos));
        }
        catch (NotFoundException notFoundException) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage()))
                    .build();
        }
        catch (NombrePassFestivalInsuffisantException nombrePassFestivalInsuffisantException) {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    nombrePassFestivalInsuffisantException.getIdFestival(),
                    nombrePassFestivalInsuffisantException.getNbPassDisponible(),
                    ErreurPaiementClass.FESTIVAL
            ));
        }
        catch (NombrePlaceCovoiturageInsuffisantException nombrePlaceCovoiturageInsuffisantException)
        {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    nombrePlaceCovoiturageInsuffisantException.getIdOffreCovoiturage(),
                    nombrePlaceCovoiturageInsuffisantException.getNbPlaceDisponible(),
                    ErreurPaiementClass.OFFRE_COVOITURAGE
            ));
        }
        catch (QuantiteZeroException quantiteZeroException) {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    quantiteZeroException.getId(),
                    0,
                    quantiteZeroException.getClassType()
            ));
        }
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.articleService.deleteArticle(id));
        } catch (NotFoundException e) {
                return ResponseEntity
                        .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage()))
                        .build();

        }
    }
}
