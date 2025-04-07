package com.apis.apiscollection.util;

import com.apis.apiscollection.application.person.dto.PersonRequestCreate;
import com.apis.apiscollection.application.person.dto.PersonRequestUpdate;
import com.apis.apiscollection.domain.address.Address;
import com.github.f4b6a3.uuid.UuidCreator;

import java.util.UUID;

public class PersonRequestCreateFactory {

    public static PersonRequestCreate buildValid() {
        return PersonRequestCreate.builder()
                .name("Tiago Test")
                .cpf("12345678900")
                .email("tiago@email.com")
                .phone("+5585988887777")
                .address(Address.builder()
                        .id(UuidCreator.getTimeOrderedEpoch())
                        .street("Street Test 01")
                        .number("1010")
                        .complement("house")
                        .neighborhood("Neighborhood Test 01")
                        .city("City Test 01")
                        .state("CE")
                        .postalCode("60000001")
                        .country("Country Test 01")
                        .build())
                .build();
    }

    public static PersonRequestUpdate buildValidUpdate() {
        return PersonRequestUpdate.builder()
                .name("Tiago Test")
                .cpf("12345678900")
                .email("tiago@email.com")
                .phone("+5585988887777")
                .build();
    }

    public static Address buildValidAddress(UUID addressId) {
        return Address.builder()
                .id(addressId)
                .street("Street Test")
                .number("1010")
                .complement("house")
                .neighborhood("Test Nighborhood")
                .city("Test City")
                .state("CE")
                .postalCode("60000001")
                .country("Brazil")
                .build();
    }
}
