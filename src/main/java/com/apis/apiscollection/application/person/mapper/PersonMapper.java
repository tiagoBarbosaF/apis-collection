package com.apis.apiscollection.application.person.mapper;

import com.apis.apiscollection.application.person.dto.PersonRequestCreate;
import com.apis.apiscollection.application.person.dto.PersonRequestUpdate;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.domain.person.Person;

import java.util.List;

public class PersonMapper {
    public static PersonResponse convertDomainToResponse(Person person) {
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .cpf(person.getCpf())
                .email(person.getEmail())
                .phone(person.getPhone())
                .address(person.getAddress())
                .build();
    }

    public static Person convertRequestUpdateToDomain(PersonRequestUpdate request) {
        return Person.builder()
                .name(request.name())
                .cpf(request.cpf())
                .email(request.email())
                .phone(request.phone())
                .build();
    }

    public static Person convertRequestCreateToDomain(PersonRequestCreate request) {
        return Person.builder()
                .name(request.name())
                .cpf(request.cpf())
                .email(request.email())
                .phone(request.phone())
                .address(List.of(request.address()))
                .build();
    }
}
