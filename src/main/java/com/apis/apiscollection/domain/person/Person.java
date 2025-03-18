package com.apis.apiscollection.domain.person;

import com.apis.apiscollection.domain.address.Address;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Person {
    private final Long id;
    private final String name;
    private final String cpf;
    private final String email;
    private final String phone;
    private final List<Address> address;

    private Person(Builder builder) {
        cpf = builder.cpf;
        id = builder.id;
        name = builder.name;
        email = builder.email;
        phone = builder.phone;
        address = builder.address;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public List<Address> getAddress() {
        return address;
    }

    public Person updatePerson(Person person) {
        return Person.builder()
                .id(this.id)
                .name(person.getName())
                .cpf(person.getCpf())
                .email(person.getEmail())
                .phone(person.getPhone())
                .address(person.getAddress())
                .build();
    }

    public Person addAddress(Address newAddress) {
        if(address == null) throw new NullPointerException("Address cannot be null");

        List<Address> updateAddresses = new ArrayList<>(this.address);
        updateAddresses.add(newAddress);
        return Person.builder()
                .id(this.id)
                .name(this.name)
                .cpf(this.cpf)
                .email(this.email)
                .phone(this.phone)
                .address(updateAddresses)
                .build();
    }
    
    public Person updateAddress(List<Address> newAddress) {
        return Person.builder()
                .id(this.id)
                .name(this.name)
                .cpf(this.cpf)
                .email(this.email)
                .phone(this.phone)
                .address(newAddress)
                .build();
    }

    public Person sortedAddressDesc(List<Address> addressesSorted) {
            List<Address> addressSortedDesc = addressesSorted.stream()
                    .sorted(Comparator.comparing(Address::getCreatedAt).reversed())
                    .toList();
            return Person.builder()
                    .id(this.id)
                    .name(this.name)
                    .cpf(this.cpf)
                    .email(this.email)
                    .phone(this.phone)
                    .address(addressSortedDesc)
                    .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String cpf;
        private Long id;
        private String name;
        private String email;
        private String phone;
        private List<Address> address;

        private Builder() {
        }

        public Builder cpf(String val) {
            cpf = val;
            return this;
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder address(List<Address> val) {
            address = val;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
