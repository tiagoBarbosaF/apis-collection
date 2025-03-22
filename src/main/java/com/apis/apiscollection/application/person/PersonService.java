package com.apis.apiscollection.application.person;

import com.apis.apiscollection.application.address.dto.AddressRequest;
import com.apis.apiscollection.application.address.dto.AddressResponse;
import com.apis.apiscollection.application.address.mapper.AddressMapper;
import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequestCreate;
import com.apis.apiscollection.application.person.dto.PersonRequestUpdate;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.application.person.mapper.PersonMapper;
import com.apis.apiscollection.application.person.port.in.PersonUseCase;
import com.apis.apiscollection.application.person.port.out.PersonRepositoryPort;
import com.apis.apiscollection.domain.address.Address;
import com.apis.apiscollection.domain.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService implements PersonUseCase {
    private final PersonRepositoryPort personRepositoryPort;

    public PersonService(PersonRepositoryPort personRepositoryPort) {
        this.personRepositoryPort = personRepositoryPort;
    }

    @Override
    public MessageResponse createPerson(PersonRequestCreate request) {
        Person person = PersonMapper.convertRequestCreateToDomain(request);
        personRepositoryPort.savePerson(person);

        return new MessageResponse("Person saved");
    }

    @Override
    public MessageResponse updatePerson(UUID id, PersonRequestUpdate request) {
        Person personFind = personRepositoryPort.findPersonById(id);
        Person requestConverted = PersonMapper.convertRequestUpdateToDomain(request);
        Person person = personFind.updatePerson(requestConverted);

        personRepositoryPort.savePerson(person);
        return new MessageResponse("Person updated");
    }

    @Override
    public void deletePerson(UUID id) {
        personRepositoryPort.deletePerson(id);
    }

    @Override
    public PersonResponse findPersonById(UUID id) {
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
    public AddressResponse findAddressById(UUID personId, UUID addressId) {
        Address addressById = getPersonAddressById(personId, addressId);
        return AddressMapper.convertDomainToResponse(addressById);
    }

    @Override
    public Page<AddressResponse> findAllPersonAddress(UUID personId, int page, int pageSize) {
        Page<Address> allPersonAddress = personRepositoryPort.findAllPersonAddress(personId, page, pageSize);
        return allPersonAddress.map(AddressMapper::convertDomainToResponse);
    }

    @Override
    public MessageResponse addNewPersonAddress(UUID personId, AddressRequest addressRequest) {
        Person personById = personRepositoryPort.findPersonById(personId);
        Address addressConverted = AddressMapper.convertRequestToDomain(addressRequest);
        Person personUpdated = personById.addAddress(addressConverted);
        personRepositoryPort.savePerson(personUpdated);

        return new MessageResponse("Person address added");
    }

    @Override
    public MessageResponse updatePersonAddress(UUID personId, UUID addressId, AddressRequest addressRequest) {
        Person personFind = personRepositoryPort.findPersonById(personId);
        Address addressToUpdate = AddressMapper.convertRequestToDomain(addressRequest);
        List<Address> addressList = personFind.getAddress().stream()
                .map(addr -> addr.getId().equals(addressId) ? addr.updateAddress(addressToUpdate) : addr).collect(Collectors.toList());

        Person personUpdated = personFind.updateAddress(addressList);
        personRepositoryPort.savePerson(personUpdated);
        return new MessageResponse("Person address updated");
    }

    @Override
    public void deletePersonAddress(UUID addressId, UUID personId) {
        personRepositoryPort.deletePersonAddressById(addressId, personId);
    }

    private Address getPersonAddressById(UUID personId, UUID addressId) {
        return personRepositoryPort.findAddressById(personId, addressId);
    }
}
