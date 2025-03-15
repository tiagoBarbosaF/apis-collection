package com.apis.apiscollection.infrastructure.person.adapter.persistence;

import com.apis.apiscollection.application.person.port.out.PersonRepositoryPort;
import com.apis.apiscollection.domain.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class PersonRepositoryAdapter implements PersonRepositoryPort {
    private final PersonRepository personRepository;
    private final PersonEntityMapper personEntityMapper;

    public PersonRepositoryAdapter(PersonRepository personRepository,
                                   PersonEntityMapper personEntityMapper) {
        this.personRepository = personRepository;
        this.personEntityMapper = personEntityMapper;
    }

    @Override
    public void savePerson(Person person) {
        PersonEntity entity = personEntityMapper.toEntity(person);
        personRepository.save(entity);
    }

    @Override
    public Person findPersonById(long id) {
        Optional<PersonEntity> personById = personRepository.findById(id);
        return personById.map(personEntityMapper::toDomain).orElse(null);
    }

    @Override
    public Page<Person> findAllPersons(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return personRepository.findAll(pageable).map(personEntityMapper::toDomain);
    }
}
