package com.rickandmorty.character.wiki.rickandmortywiki.service;

import com.rickandmorty.character.wiki.rickandmortywiki.dto.external.ApiCharacter;
import com.rickandmorty.character.wiki.rickandmortywiki.dto.external.ApiInfo;
import com.rickandmorty.character.wiki.rickandmortywiki.dto.external.ApiResponse;
import com.rickandmorty.character.wiki.rickandmortywiki.model.MovieCharacter;
import com.rickandmorty.character.wiki.rickandmortywiki.repository.MovieCharacterRepository;
import com.rickandmorty.character.wiki.rickandmortywiki.service.mapper.ApiCharacterRequestMapper;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DataSynchronizerTest {
    @InjectMocks
    private DataSynchronizer dataSynchronizer;
    @Spy
    private ApiCharacterRequestMapper mapper;
    @Mock
    private ApiClient client;
    @Mock
    private MovieCharacterRepository characterRepository;

    @Test
    public void returnExpected_filterAndMap_Ok() {
        Mockito.when(characterRepository.getAllExternalIndexes())
                .thenReturn(Collections.emptySet());
        List<MovieCharacter> expected = List.of(
                new MovieCharacter(null, 1, "Rick Sanchez",
                        MovieCharacter.CharacterStatus.ALIVE, MovieCharacter.CharacterGender.MALE),
                new MovieCharacter(null, 2, "Morty Smith",
                        MovieCharacter.CharacterStatus.ALIVE, MovieCharacter.CharacterGender.MALE));
        List<ApiCharacter> initial = List.of(
                new ApiCharacter(1, "Rick Sanchez", "Alive", "Male"),
                new ApiCharacter(2, "Morty Smith", "Alive", "Male"));
        List<MovieCharacter> actual = dataSynchronizer.filterAndMap(initial);
        Assertions.assertTrue(CollectionUtils.isEqualCollection(expected, actual));
    }

    @Test
    public void returnEmptyList_filterAndMap_Ok() {
        Mockito.when(characterRepository.getAllExternalIndexes()).thenReturn(Set.of(1, 2));
        List<MovieCharacter> expected = Collections.emptyList();
        List<ApiCharacter> initial = List.of(
                new ApiCharacter(1, "Rick Sanchez", "Alive", "Male"),
                new ApiCharacter(2, "Morty Smith", "Alive", "Male"));
        List<MovieCharacter> actual = dataSynchronizer.filterAndMap(initial);
        Assertions.assertTrue(CollectionUtils.isEqualCollection(expected, actual));
    }

    @Test
    public void returnExpected_fetchData_Ok() {
        Mockito.when(client.getCharactersPaginated(1))
                .thenReturn(new ApiResponse(new ApiInfo(2, 1, null), List.of(
                        new ApiCharacter(1, "Rick Sanchez", "Alive", "Male"),
                        new ApiCharacter(2, "Morty Smith", "Alive", "Male"))));
        List<ApiCharacter> expected = List.of(
                new ApiCharacter(1, "Rick Sanchez", "Alive", "Male"),
                new ApiCharacter(2, "Morty Smith", "Alive", "Male"));
        List<ApiCharacter> actual = dataSynchronizer.fetchData();
        Assertions.assertTrue(CollectionUtils.isEqualCollection(expected, actual));
    }
}
