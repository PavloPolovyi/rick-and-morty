package com.rickandmorty.character.wiki.rickandmortywiki.controller;

import com.rickandmorty.character.wiki.rickandmortywiki.model.MovieCharacter;
import com.rickandmorty.character.wiki.rickandmortywiki.service.CharacterService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieCharacterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CharacterService characterService;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void returnExpected_getRandomCharacter_Ok() {
        String characterName = "Rick Sanchez";
        Mockito.when(characterService.getRandomCharacter())
                .thenReturn(new MovieCharacter(1L, 1, characterName,
                MovieCharacter.CharacterStatus.ALIVE, MovieCharacter.CharacterGender.MALE));
        RestAssuredMockMvc.when()
                .get("/movie-characters/random")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo(characterName))
                .body("status", Matchers.equalTo(MovieCharacter.CharacterStatus.ALIVE.name()))
                .body("gender", Matchers.equalTo(MovieCharacter.CharacterGender.MALE.name()));
    }

    @Test
    public void returnExpected_findAllByNameContains_Ok() {
        String nameLike = "orty";
        Mockito.when(characterService.findByNameContains(nameLike))
                .thenReturn(List.of(
                        new MovieCharacter(2L, 2, "Morty Smith",
                                MovieCharacter.CharacterStatus.ALIVE,
                                MovieCharacter.CharacterGender.MALE)));
        RestAssuredMockMvc.when()
                .get("/movie-characters/by-name?nameLike=" + nameLike)
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(1))
                .body("[0].id", Matchers.equalTo(2))
                .body("[0].name", Matchers.equalTo("Morty Smith"))
                .body("[0].status", Matchers.equalTo(MovieCharacter.CharacterStatus.ALIVE.name()))
                .body("[0].gender", Matchers.equalTo(MovieCharacter.CharacterGender.MALE.name()));
    }
}
