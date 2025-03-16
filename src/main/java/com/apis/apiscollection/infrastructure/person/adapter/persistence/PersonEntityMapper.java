package com.apis.apiscollection.infrastructure.person.adapter.persistence;

import com.apis.apiscollection.domain.person.Person;
import com.apis.apiscollection.infrastructure.address.adapter.persistence.AddressEntityMapper;
import org.springframework.stereotype.Component;

@Component
class PersonEntityMapper {

    public PersonEntity toEntity(Person person) {
        if (person == null) return null;

        return PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .cpf(person.getCpf())
                .email(person.getEmail())
                .phone(person.getPhone())
                .addresses(person.getAddress().stream().map(AddressEntityMapper::domainToEntity).toList())
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
                .address(personEntity.getAddresses().stream().map(AddressEntityMapper::entityToDomain).toList())
                .build();
    }
}
