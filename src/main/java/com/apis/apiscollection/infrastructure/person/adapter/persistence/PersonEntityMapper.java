package com.apis.apiscollection.infrastructure.person.adapter.persistence;

import com.apis.apiscollection.domain.person.Person;
import org.springframework.stereotype.Component;

@Component
class PersonEntityMapper {

    public PersonEntity toEntity(Person person) {
        if (person == null) return null;

        return PersonEntity.builder()
                .name(person.getName())
                .cpf(person.getCpf())
                .email(person.getEmail())
                .phone(person.getPhone())
                .build();
    }

    public Person toDomain(PersonEntity personEntity) {
        if (personEntity == null) return null;

        return Person.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .cpf(personEntity.getCpf())
                .email(personEntity.getEmail())
                .phone(personEntity.getPhone())
                .build();
    }
}
