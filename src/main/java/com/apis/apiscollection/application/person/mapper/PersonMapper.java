package com.apis.apiscollection.application.person.mapper;

import com.apis.apiscollection.application.address.dto.AddressRequest;
import com.apis.apiscollection.application.address.dto.AddressResponse;
import com.apis.apiscollection.application.person.dto.PersonRequest;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.domain.address.Address;
import com.apis.apiscollection.domain.person.Person;

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
        return Person.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }

    public static AddressResponse convertAddressToResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }

    public static Address convertRequestToAddress(AddressRequest request) {
        return Address.builder()
                .street(request.street())
                .number(request.number())
                .complement(request.complement())
                .neighborhood(request.neighborhood())
                .city(request.city())
                .state(request.state())
                .postalCode(request.postalCode())
                .country(request.country())
                .build();
    }
}
