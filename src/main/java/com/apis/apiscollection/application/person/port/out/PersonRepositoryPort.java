package com.apis.apiscollection.application.person.port.out;

import com.apis.apiscollection.domain.address.Address;
import com.apis.apiscollection.domain.person.Person;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PersonRepositoryPort {
    void savePerson(Person person);
    void deletePerson(UUID id);
    Person findPersonById (UUID id);
    Page<Person> findAllPersons(int page, int pageSize);

    Address findAddressById(UUID personId, UUID addressId);
    void deletePersonAddressById(UUID addressId, UUID personId);
}
