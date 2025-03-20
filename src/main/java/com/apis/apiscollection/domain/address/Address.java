package com.apis.apiscollection.domain.address;

import java.util.UUID;

public class Address {
    private UUID id;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    protected Address() {
    }

    private Address(Builder builder) {
        city = builder.city;
        id = builder.id;
        street = builder.street;
        number = builder.number;
        complement = builder.complement;
        neighborhood = builder.neighborhood;
        state = builder.state;
        postalCode = builder.postalCode;
        country = builder.country;
    }

    public String getCity() {
        return city;
    }

    public String getComplement() {
        return complement;
    }

    public String getCountry() {
        return country;
    }

    public UUID getId() {
        return id;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getNumber() {
        return number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public Address updateAddress(Address newAddress) {
        if (newAddress == null) throw new IllegalArgumentException("New address cannot be null");

        if (newAddress.equals(this)) return this;

        return Address.builder()
                .id(this.id)
                .street(newAddress.getStreet() == null ? this.street : newAddress.getStreet())
                .number(newAddress.getNumber() == null ? this.number : newAddress.getNumber())
                .complement(newAddress.getComplement() == null ? this.complement : newAddress.getComplement())
                .neighborhood(newAddress.getNeighborhood() == null ? this.neighborhood : newAddress.getNeighborhood())
                .city(newAddress.getCity() == null ? this.city : newAddress.getCity())
                .state(newAddress.getState() == null ? this.state : newAddress.getState())
                .postalCode(newAddress.getPostalCode() == null ? this.postalCode : newAddress.getPostalCode())
                .country(newAddress.getCountry() == null ? this.country : newAddress.getCountry())
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String city;
        private UUID id;
        private String street;
        private String number;
        private String complement;
        private String neighborhood;
        private String state;
        private String postalCode;
        private String country;

        public Builder() {
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder street(String val) {
            street = val;
            return this;
        }

        public Builder number(String val) {
            number = val;
            return this;
        }

        public Builder complement(String val) {
            complement = val;
            return this;
        }

        public Builder neighborhood(String val) {
            neighborhood = val;
            return this;
        }

        public Builder state(String val) {
            state = val;
            return this;
        }

        public Builder postalCode(String val) {
            postalCode = val;
            return this;
        }

        public Builder country(String val) {
            country = val;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
