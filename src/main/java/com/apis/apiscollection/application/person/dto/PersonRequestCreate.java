package com.apis.apiscollection.application.person.dto;

import com.apis.apiscollection.domain.address.Address;

public record PersonRequestCreate(
        String name,
        String cpf,
        String email,
        String phone,
        Address address
) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String cpf;
        private String name;
        private String email;
        private String phone;
        private Address address;

        public Builder() {
        }

        public Builder cpf(String val) {
            cpf = val;
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

        public Builder address(Address val) {
            address = val;
            return this;
        }

        public PersonRequestCreate build() {
            return new PersonRequestCreate(
                    this.name,
                    this.cpf,
                    this.email,
                    this.phone,
                    this.address
            );
        }
    }
}
