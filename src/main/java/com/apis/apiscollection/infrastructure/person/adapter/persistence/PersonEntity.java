package com.apis.apiscollection.infrastructure.person.adapter.persistence;

import com.apis.apiscollection.infrastructure.address.adapter.persistence.AddressEntity;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person", orphanRemoval = true)
    private List<AddressEntity> addresses;

    public PersonEntity() {
    }

    private PersonEntity(Builder builder) {
        id = builder.id;
        name = builder.name;
        cpf = builder.cpf;
        email = builder.email;
        phone = builder.phone;
        addresses = builder.addresses;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String cpf;
        private String name;
        private String email;
        private String phone;
        private List<AddressEntity> addresses;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder cpf(String val) {
            cpf = val;
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

        public Builder addresses(List<AddressEntity> val) {
            addresses = val;
            return this;
        }

        public PersonEntity build() {
            return new PersonEntity(this);
        }
    }
}
