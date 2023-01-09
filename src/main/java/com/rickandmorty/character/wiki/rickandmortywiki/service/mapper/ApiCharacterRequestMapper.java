package com.rickandmorty.character.wiki.rickandmortywiki.service.mapper;

import com.rickandmorty.character.wiki.rickandmortywiki.dto.external.ApiCharacter;
import com.rickandmorty.character.wiki.rickandmortywiki.model.MovieCharacter;
import org.springframework.stereotype.Component;

@Component
public class ApiCharacterRequestMapper implements RequestMapper<MovieCharacter, ApiCharacter> {
    public MovieCharacter toModel(ApiCharacter apiCharacter) {
        return new MovieCharacter()
                .setExternalId(apiCharacter.getId())
                .setName(apiCharacter.getName())
                .setStatus(MovieCharacter.CharacterStatus
                        .valueOf(apiCharacter.getStatus().toUpperCase()))
                .setGender(MovieCharacter.CharacterGender
                        .valueOf(apiCharacter.getGender().toUpperCase()));
    }
}
