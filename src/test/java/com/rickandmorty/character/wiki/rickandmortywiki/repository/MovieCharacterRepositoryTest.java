package com.rickandmorty.character.wiki.rickandmortywiki.repository;

import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class MovieCharacterRepositoryTest {
    @Container
    private static final PostgreSQLContainer<?> dataBase =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:15-alpine"))
                    .withUsername("postgres")
                    .withPassword("postgres")
                    .withDatabaseName("rick_morty_db");
    @Autowired
    private MovieCharacterRepository repository;

    @DynamicPropertySource
    public static void setDataSourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", dataBase::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", dataBase::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", dataBase::getPassword);
    }

    @Test
    @Sql("/scripts/init.sql")
    public void shouldReturnAllExternalIndexes_getAllExternalIndexes_Ok() {
        Set<Integer> actual = repository.getAllExternalIndexes();
        Set<Integer> expected = Set.of(1, 2, 3);
        Assertions.assertTrue(CollectionUtils.isEqualCollection(expected, actual));
    }
}
