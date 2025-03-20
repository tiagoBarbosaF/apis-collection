package com.apis.apiscollection.infrastructure.person.adapter.persistence;

import com.apis.apiscollection.application.person.port.out.PersonRepositoryPort;
import com.apis.apiscollection.domain.address.Address;
import com.apis.apiscollection.domain.person.Person;
import com.apis.apiscollection.infrastructure.address.adapter.persistence.AddressEntityMapper;
import com.apis.apiscollection.infrastructure.address.adapter.persistence.AddressRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
class PersonRepositoryAdapter implements PersonRepositoryPort {
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final PersonEntityMapper personEntityMapper;

    public PersonRepositoryAdapter(PersonRepository personRepository,
                                   AddressRepository addressRepository,
                                   PersonEntityMapper personEntityMapper) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.personEntityMapper = personEntityMapper;
    }

    @Transactional
    @Override
    public void savePerson(Person person) {
        PersonEntity entity = personEntityMapper.toEntity(person);
        personRepository.save(entity);
    }

    @Transactional
    @Override
    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person findPersonById(UUID id) {
        Optional<PersonEntity> personById = personRepository.findById(id);
        return personById.map(personEntityMapper::toDomain).orElse(null);
    }

    @Override
    public Page<Person> findAllPersons(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return personRepository.findAll(pageable).map(personEntityMapper::toDomain);
    }

    @Override
    public Address findAddressById(UUID personId, UUID addressId) {
        return addressRepository.findByIdAndPersonId(addressId, personId)
                .map(AddressEntityMapper::entityToDomain)
                .orElseThrow(() -> new RuntimeException("Address not found."));
    }

    @Transactional
    @Override
    public void deletePersonAddressById(UUID addressId, UUID personId) {
        addressRepository.deleteByIdAndPersonId(addressId, personId);
    }
}
