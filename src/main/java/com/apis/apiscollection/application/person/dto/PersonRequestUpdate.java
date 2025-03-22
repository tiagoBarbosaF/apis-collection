package com.apis.apiscollection.application.person.dto;

public record PersonRequestUpdate(
        String name,
        String cpf,
        String email,
        String phone
) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String cpf;
        private String name;
        private String email;
        private String phone;

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

        public PersonRequestUpdate build() {
            return new PersonRequestUpdate(
                    this.name,
                    this.cpf,
                    this.email,
                    this.phone
            );
        }
    }
}
