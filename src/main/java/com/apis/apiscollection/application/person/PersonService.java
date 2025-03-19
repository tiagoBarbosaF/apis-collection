package com.apis.apiscollection.application.person;

import com.apis.apiscollection.application.address.dto.AddressRequest;
import com.apis.apiscollection.application.address.dto.AddressResponse;
import com.apis.apiscollection.application.address.mapper.AddressMapper;
import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequest;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.application.person.mapper.PersonMapper;
import com.apis.apiscollection.application.person.port.in.PersonUseCase;
import com.apis.apiscollection.application.person.port.out.PersonRepositoryPort;
import com.apis.apiscollection.domain.address.Address;
import com.apis.apiscollection.domain.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public MessageResponse updatePerson(Long id, PersonRequest request) {
        Person personFind = personRepositoryPort.findPersonById(id);
        Person requestConverted = PersonMapper.convertRequestToDomain(request);
        Person person = personFind.updatePerson(requestConverted);

        personRepositoryPort.savePerson(person);
        return new MessageResponse("Person updated");
    }

    @Override
    public void deletePerson(Long id) {
        personRepositoryPort.deletePerson(id);
    }

    @Override
    public PersonResponse findPersonById(Long id) {
        Person personById = personRepositoryPort.findPersonById(id);
        Person personUpdated = personById.sortedAddressDesc(personById.getAddress());
        return PersonMapper.convertDomainToResponse(personUpdated);
    }

    @Override
    public Page<PersonResponse> findAllPersons(int page, int pageSize) {
        Page<Person> allPersons = personRepositoryPort.findAllPersons(page, pageSize);
        return allPersons.map(person ->
                PersonMapper.convertDomainToResponse(person.sortedAddressDesc(person.getAddress())));
    }

    @Override
    public AddressResponse findAddressById(Long personId, Long addressId) {
        Address addressById = getPersonAddressById(personId, addressId);
        return AddressMapper.convertDomainToResponse(addressById);
    }

    @Override
    public MessageResponse addNewPersonAddress(Long personId, AddressRequest addressRequest) {
        Person personById = personRepositoryPort.findPersonById(personId);
        Address addressConverted = PersonMapper.convertRequestToAddress(addressRequest);
        Person personUpdated = personById.addAddress(addressConverted);
        personRepositoryPort.savePerson(personUpdated);

        return new MessageResponse("Person address added");
    }

    @Override
    public MessageResponse updatePersonAddress(Long personId, Long addressId, AddressRequest addressRequest) {
        Person personFind = personRepositoryPort.findPersonById(personId);
        Address addressToUpdate = PersonMapper.convertRequestToAddress(addressRequest);
        List<Address> addressList = personFind.getAddress().stream()
                .map(addr -> addr.getId().equals(addressId) ? addr.updateAddress(addressToUpdate) : addr).collect(Collectors.toList());

        Person personUpdated = personFind.updateAddress(addressList);
        personRepositoryPort.savePerson(personUpdated);
        return new MessageResponse("Person address updated");
    }

    @Override
    public void deletePersonAddress(Long addressId, Long personId) {
        personRepositoryPort.deletePersonAddressById(addressId, personId);
    }

    private Address getPersonAddressById(Long personId, Long addressId) {
        return personRepositoryPort.findAddressById(personId, addressId);
    }
}
