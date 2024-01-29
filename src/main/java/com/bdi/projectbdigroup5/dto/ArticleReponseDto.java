package com.bdi.projectbdigroup5.dto;

import com.bdi.projectbdigroup5.model.Festival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ArticleReponseDto {
    private Festival festival;
    private int quantite;
}
