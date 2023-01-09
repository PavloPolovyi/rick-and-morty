package com.rickandmorty.character.wiki.rickandmortywiki.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "characters")
public class MovieCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ids_sequence")
    @SequenceGenerator(name = "ids_sequence", sequenceName = "ids_sequence", allocationSize = 1)
    private Long id;
    @Column(name = "external_id")
    private Integer externalId;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private CharacterStatus status;
    @Enumerated(value = EnumType.STRING)
    private CharacterGender gender;

    public enum CharacterStatus {
        ALIVE("Alive"), DEAD("Dead"), UNKNOWN("unknown");
        private final String status;

        CharacterStatus(String status) {
            this.status = status;
        }
    }

    public enum CharacterGender {
        FEMALE("Female"), MALE("Male"), GENDERLESS("Genderless"), UNKNOWN("unknown");
        private final String gender;

        CharacterGender(String gender) {
            this.gender = gender;
        }
    }
}
