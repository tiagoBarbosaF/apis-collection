package com.apis.apiscollection.application.address.dto;

public record AddressRequest(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String postalCode,
        String country
) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
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

        public AddressRequest build() {
            return new AddressRequest(
                    this.street,
                    this.number,
                    this.complement,
                    this.neighborhood,
                    this.city,
                    this.state,
                    this.postalCode,
                    this.country
            );
        }
    }
}
