package com.rickandmorty.character.wiki.rickandmortywiki.service.mapper;

import com.rickandmorty.character.wiki.rickandmortywiki.dto.MovieCharacterResponseDto;
import com.rickandmorty.character.wiki.rickandmortywiki.model.MovieCharacter;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterResponseMapper
        implements ResponseMapper<MovieCharacterResponseDto, MovieCharacter> {
    @Override
    public MovieCharacterResponseDto toDto(MovieCharacter movieCharacter) {
        return new MovieCharacterResponseDto()
                .setId(movieCharacter.getId())
                .setName(movieCharacter.getName())
                .setGender(movieCharacter.getGender())
                .setStatus(movieCharacter.getStatus());
    }
}
