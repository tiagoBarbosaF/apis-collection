package com.apis.apiscollection.infrastructure.address.adapter.persistence;

import com.apis.apiscollection.infrastructure.person.adapter.persistence.PersonEntity;
import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    private UUID id;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_person_id", nullable = false)
    private PersonEntity person;

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public AddressEntity() {
    }

    private AddressEntity(Builder builder) {
        id = builder.id;
        street = builder.street;
        number = builder.number;
        complement = builder.complement;
        neighborhood = builder.neighborhood;
        city = builder.city;
        state = builder.state;
        postalCode = builder.postalCode;
        country = builder.country;
    }

    @PrePersist
    protected void prePersist() {
        if (id == null) id = UuidCreator.getTimeOrderedEpoch();
    }

    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String street;
        private String number;
        private String complement;
        private String neighborhood;
        private String city;
        private String state;
        private String postalCode;
        private String country;

        public Builder() {
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

        public Builder city(String val) {
            city = val;
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

        public AddressEntity build() {
            return new AddressEntity(this);
        }
    }
}
