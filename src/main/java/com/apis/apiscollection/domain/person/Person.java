package com.apis.apiscollection.domain.person;

public class Person {
    private final long id;
    private final String name;
    private final String cpf;
    private final String email;
    private final String phone;

    private Person(Builder builder) {
        cpf = builder.cpf;
        id = builder.id;
        name = builder.name;
        email = builder.email;
        phone = builder.phone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Person updatePerson(Person person) {
        return Person.builder()
                .id(this.id)
                .name(person.getName())
                .cpf(person.getCpf())
                .email(person.getEmail())
                .phone(person.getPhone())
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String cpf;
        private long id;
        private String name;
        private String email;
        private String phone;

        public Builder() {
        }

        public Builder cpf(String val) {
            cpf = val;
            return this;
        }

        public Builder id(long val) {
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

        public Person build() {
            return new Person(this);
        }
    }
}
