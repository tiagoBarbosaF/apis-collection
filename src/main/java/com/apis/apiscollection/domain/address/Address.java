package com.apis.apiscollection.domain.address;

import java.time.Instant;

public class Address {
    private Long id;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Instant createdAt;

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
        createdAt = builder.createdAt;
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

    public Long getId() {
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Address updateAddress(Address newAddress) {
        if (newAddress == null) throw new IllegalArgumentException("New address cannot be null");

        if (newAddress.equals(this)) return this;

        return Address.builder()
                .id(this.id)
                .street(newAddress.getStreet())
                .number(newAddress.getNumber())
                .complement(newAddress.getComplement())
                .neighborhood(newAddress.getNeighborhood())
                .city(newAddress.getCity())
                .state(newAddress.getState())
                .postalCode(newAddress.getPostalCode())
                .country(newAddress.getCountry())
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String city;
        private Long id;
        private String street;
        private String number;
        private String complement;
        private String neighborhood;
        private String state;
        private String postalCode;
        private String country;
        private Instant createdAt;

        public Builder() {
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public Builder id(Long val) {
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

        public Builder createdAt(Instant val) {
            createdAt = val;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
