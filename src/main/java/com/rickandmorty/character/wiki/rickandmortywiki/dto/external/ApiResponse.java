package com.rickandmorty.character.wiki.rickandmortywiki.dto.external;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse {
    private ApiInfo info;
    private List<ApiCharacter> results;
}
