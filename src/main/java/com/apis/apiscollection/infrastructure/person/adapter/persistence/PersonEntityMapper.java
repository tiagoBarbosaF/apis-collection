package com.apis.apiscollection.infrastructure.person.adapter.persistence;

import com.apis.apiscollection.domain.address.Address;
import com.apis.apiscollection.domain.person.Person;
import com.apis.apiscollection.infrastructure.address.adapter.persistence.AddressEntity;
import com.apis.apiscollection.infrastructure.address.adapter.persistence.AddressEntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
class PersonEntityMapper {

    public PersonEntity toEntity(Person person) {
        if (person == null) return null;

        PersonEntity entity = PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .cpf(person.getCpf())
                .email(person.getEmail())
                .phone(person.getPhone())
                .addresses(new ArrayList<>())
                .build();
        for (Address address : person.getAddress()) {
            AddressEntity addressEntity = AddressEntityMapper.domainToEntity(address);
            addressEntity.setPerson(entity);
            entity.getAddresses().add(addressEntity);
        }

        return entity;
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
