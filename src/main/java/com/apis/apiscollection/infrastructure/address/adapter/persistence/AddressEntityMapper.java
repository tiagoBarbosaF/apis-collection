package com.apis.apiscollection.infrastructure.address.adapter.persistence;

import com.apis.apiscollection.domain.address.Address;

import java.time.Instant;

public class AddressEntityMapper {
    public static AddressEntity domainToEntity(Address address) {
        if (address == null) return null;

        return AddressEntity.builder()
                .id(address.getId() == null ? null : address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .createdAt(address.getCreatedAt() != null ? address.getCreatedAt() : Instant.now())
                .build();
    }

    public static Address entityToDomain(AddressEntity entity) {
        if (entity == null) return null;

        return Address.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .number(entity.getNumber())
                .complement(entity.getComplement())
                .neighborhood(entity.getNeighborhood())
                .city(entity.getCity())
                .state(entity.getState())
                .postalCode(entity.getPostalCode())
                .country(entity.getCountry())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
