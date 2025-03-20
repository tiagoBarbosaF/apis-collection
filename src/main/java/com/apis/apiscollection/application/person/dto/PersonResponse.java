package com.apis.apiscollection.application.person.dto;

import com.apis.apiscollection.domain.address.Address;

import java.util.List;
import java.util.UUID;

public record PersonResponse(
        UUID id,
        String name,
        String cpf,
        String email,
        String phone,
        List<Address> addresses
) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String cpf;
        private UUID id;
        private String name;
        private String email;
        private String phone;
        private List<Address> addresses;

        public Builder() {
        }

        public Builder cpf(String val) {
            cpf = val;
            return this;
        }

        public Builder id(UUID val) {
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

        public Builder address(List<Address>  val) {
            addresses = val;
            return this;
        }

        public PersonResponse build() {
            return new PersonResponse(
                    this.id,
                    this.name,
                    this.cpf,
                    this.email,
                    this.phone,
                    this.addresses
            );
        }
    }
}
