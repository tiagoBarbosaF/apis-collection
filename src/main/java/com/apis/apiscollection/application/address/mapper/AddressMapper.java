package com.apis.apiscollection.application.address.mapper;

import com.apis.apiscollection.application.address.dto.AddressResponse;
import com.apis.apiscollection.domain.address.Address;

public class AddressMapper {
    public static AddressResponse convertDomainToResponse(Address address) {
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

    public static Address convertRequestToDomain(AddressResponse response) {
        return Address.builder()
                .id(response.id())
                .street(response.street())
                .number(response.number())
                .complement(response.complement())
                .neighborhood(response.neighborhood())
                .city(response.city())
                .state(response.state())
                .postalCode(response.postalCode())
                .country(response.country())
                .build();
    }
}
