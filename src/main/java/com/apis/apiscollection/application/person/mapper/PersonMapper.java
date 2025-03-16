package com.apis.apiscollection.application.person.mapper;

import com.apis.apiscollection.application.person.dto.PersonRequest;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.domain.address.Address;
import com.apis.apiscollection.domain.person.Person;

import java.util.ArrayList;
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

    public static Person convertRequestToDomain(PersonRequest request) {
        List<Address> addresses = new ArrayList<>();
        addresses.add(request.getAddress());

        return Person.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(addresses)
                .build();
    }
}
