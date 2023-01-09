package com.rickandmorty.character.wiki.rickandmortywiki.repository;

import com.rickandmorty.character.wiki.rickandmortywiki.model.MovieCharacter;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
    List<MovieCharacter> findByNameContains(String substring);

    @Query("SELECT m.externalId FROM MovieCharacter m")
    Set<Integer> getAllExternalIndexes();
}
