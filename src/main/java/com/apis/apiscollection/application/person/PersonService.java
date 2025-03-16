package com.apis.apiscollection.application.person;

import com.apis.apiscollection.application.address.dto.AddressResponse;
import com.apis.apiscollection.application.address.mapper.AddressMapper;
import com.apis.apiscollection.application.person.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequest;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.application.person.mapper.PersonMapper;
import com.apis.apiscollection.application.person.port.in.PersonUseCase;
import com.apis.apiscollection.application.person.port.out.PersonRepositoryPort;
import com.apis.apiscollection.domain.address.Address;
import com.apis.apiscollection.domain.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements PersonUseCase {
    private final PersonRepositoryPort personRepositoryPort;

    public PersonService(PersonRepositoryPort personRepositoryPort) {
        this.personRepositoryPort = personRepositoryPort;
    }

    @Override
    public MessageResponse createPerson(PersonRequest request) {
        Person person = PersonMapper.convertRequestToDomain(request);
        personRepositoryPort.savePerson(person);

        return new MessageResponse("Person saved");
    }

    @Override
    public MessageResponse updatePerson(long id, PersonRequest request) {
        Person personFind = personRepositoryPort.findPersonById(id);
        Person requestConverted = PersonMapper.convertRequestToDomain(request);
        Person person = personFind.updatePerson(requestConverted);

        personRepositoryPort.savePerson(person);
        return new MessageResponse("Person updated");
    }

    @Override
    public void deletePerson(long id) {
        personRepositoryPort.deletePerson(id);
    }

    @Override
    public PersonResponse findPersonById(long id) {
        Person personById = personRepositoryPort.findPersonById(id);
        return PersonMapper.convertDomainToResponse(personById);
    }

    @Override
    public Page<PersonResponse> findAllPersons(int page, int pageSize) {
        Page<Person> allPersons = personRepositoryPort.findAllPersons(page, pageSize);
        return allPersons.map(PersonMapper::convertDomainToResponse);
    }

    @Override
    public AddressResponse findAddressById(long personId, long addressId) {
        Address addressById = personRepositoryPort.findAddressById(personId, addressId);
        return AddressMapper.convertDomainToResponse(addressById);
    }
}
