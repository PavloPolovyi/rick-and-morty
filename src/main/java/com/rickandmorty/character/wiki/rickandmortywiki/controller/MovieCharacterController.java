package com.rickandmorty.character.wiki.rickandmortywiki.controller;

import com.rickandmorty.character.wiki.rickandmortywiki.dto.MovieCharacterResponseDto;
import com.rickandmorty.character.wiki.rickandmortywiki.model.MovieCharacter;
import com.rickandmorty.character.wiki.rickandmortywiki.service.CharacterService;
import com.rickandmorty.character.wiki.rickandmortywiki.service.mapper.ResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick&Morty Characters", description = "Provides two "
        + "endpoints for getting wiki info about Rick&Morty characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/movie-characters")
public class MovieCharacterController {
    private final CharacterService characterService;
    private final ResponseMapper<MovieCharacterResponseDto, MovieCharacter> responseMapper;

    @Operation(summary = "Getting wiki info about random Rick&Morty character")
    @GetMapping("/random")
    public MovieCharacterResponseDto getRandomCharacter() {
        return responseMapper.toDto(characterService.getRandomCharacter());
    }

    @Operation(summary = "Getting wiki info about Rick&Morty characters based on "
            + "{nameLike} query parameter")
    @GetMapping("/by-name")
    public List<MovieCharacterResponseDto> findAllByNameContains(
            @RequestParam
            @Parameter(description = "query parameter for searching "
                    + "characters with name like this", example = "ick")
            String nameLike) {
        return characterService.findByNameContains(nameLike).stream()
                .map(responseMapper::toDto)
                .toList();
    }
}
