package com.bdi.projectbdigroup5.dto;

import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.Panier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PanierResponseDto {
    private Panier panier;
    private List<ArticleReponseDto> articles;
}
