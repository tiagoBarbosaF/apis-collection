package com.apis.apiscollection.application.person.port.out;

import com.apis.apiscollection.domain.person.Person;
import org.springframework.data.domain.Page;

public interface PersonRepositoryPort {

    void savePerson(Person person);
    Person findPersonById (long id);
    Page<Person> findAllPersons(int page, int pageSize);
}
