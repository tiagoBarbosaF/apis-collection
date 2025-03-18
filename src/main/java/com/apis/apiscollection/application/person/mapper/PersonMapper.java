package com.apis.apiscollection.application.person.mapper;

import com.apis.apiscollection.application.address.dto.AddressRequest;
import com.apis.apiscollection.application.address.dto.AddressResponse;
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
                .createdAt(address.getCreatedAt())
                .build();
    }

    public static Address convertRequestToAddress(AddressRequest response) {
        return Address.builder()
                .street(response.street())
                .number(response.number())
                .complement(response.complement())
                .neighborhood(response.neighborhood())
                .city(response.city())
                .state(response.state())
                .postalCode(response.postalCode())
                .country(response.country())
                .createdAt(response.createdAt())
                .build();
    }
}
