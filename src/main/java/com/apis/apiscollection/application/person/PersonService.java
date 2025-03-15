package com.apis.apiscollection.application.person;

import com.apis.apiscollection.application.person.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequest;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.application.person.port.in.PersonUseCase;
import com.apis.apiscollection.application.person.port.out.PersonRepositoryPort;
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
        Person person = Person.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        personRepositoryPort.savePerson(person);

        return new MessageResponse("Person saved");
    }

    @Override
    public MessageResponse updatePerson(long id, PersonRequest request) {
        Person personFind = personRepositoryPort.findPersonById(id);
        Person requestConverted = this.convertRequestToDomain(request);
        Person person = personFind.updatePerson(requestConverted);

        personRepositoryPort.savePerson(person);
        return new MessageResponse("Person updated");
    }

    @Override
    public PersonResponse findPersonById(long id) {
        Person personById = personRepositoryPort.findPersonById(id);
        return this.convertDomainToResponse(personById);
    }

    @Override
    public Page<PersonResponse> findAllPersons(int page, int pageSize) {
        Page<Person> allPersons = personRepositoryPort.findAllPersons(page, pageSize);
        return allPersons.map(this::convertDomainToResponse);
    }

    private PersonResponse convertDomainToResponse(Person person) {
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .cpf(person.getCpf())
                .email(person.getEmail())
                .phone(person.getPhone())
                .build();
    }

    private Person convertRequestToDomain(PersonRequest request) {
        return Person.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }
}
