package com.apis.apiscollection.application.person.port.out;

import com.apis.apiscollection.domain.address.Address;
import com.apis.apiscollection.domain.person.Person;
import org.springframework.data.domain.Page;

public interface PersonRepositoryPort {
    void savePerson(Person person);
    void deletePerson(Long id);
    Person findPersonById (Long id);
    Page<Person> findAllPersons(int page, int pageSize);

    Address findAddressById(Long personId, Long addressId);
    void deletePersonAddressById(Long addressId, Long personId);
}
