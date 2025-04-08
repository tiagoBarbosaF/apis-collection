package com.apis.apiscollection.application.person;

import com.apis.apiscollection.application.address.dto.AddressRequest;
import com.apis.apiscollection.application.address.dto.AddressResponse;
import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequestCreate;
import com.apis.apiscollection.application.person.dto.PersonRequestUpdate;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.application.person.mapper.PersonMapper;
import com.apis.apiscollection.application.person.port.out.PersonRepositoryPort;
import com.apis.apiscollection.domain.address.Address;
import com.apis.apiscollection.domain.person.Person;
import com.apis.apiscollection.util.PersonRequestCreateFactory;
import com.github.f4b6a3.uuid.UuidCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepositoryPort personRepositoryPort;

    @InjectMocks
    private PersonService personService;

    @Test
    void givenValidPersonRequest_whenCreatePerson_thenReturnMessageConfirmation() {
        //given
        PersonRequestCreate personRequestCreate = PersonRequestCreateFactory.buildValid();

        //when
        MessageResponse response = personService.createPerson(personRequestCreate);

        //then
        assertNotNull(response);
        assertEquals("Person saved", response.message());

        //verify
        verify(personRepositoryPort, times(1)).savePerson(any());
    }

    @Test
    void givenValidPersonRequest_whenUpdatePerson_thenReturnMessageConfirmation() {
        //given
        UUID uuidMock = UuidCreator.getTimeOrderedEpoch();
        PersonRequestUpdate personRequestUpdate = PersonRequestCreateFactory.buildValidUpdate();
        Person person = PersonMapper.convertRequestUpdateToDomain(personRequestUpdate);
        Person personToCheck = Person.builder()
                .id(uuidMock)
                .name(person.getName())
                .cpf(person.getCpf())
                .email(person.getEmail())
                .phone(person.getPhone())
                .address(person.getAddress())
                .build();

        //when
        when(personRepositoryPort.findPersonById(uuidMock)).thenReturn(personToCheck);
        MessageResponse response = personService.updatePerson(uuidMock, personRequestUpdate);

        //then
        assertNotNull(response);
        assertEquals("Person updated", response.message());

        //verify
        verify(personRepositoryPort, times(1)).findPersonById(uuidMock);
        verify(personRepositoryPort, times(1)).savePerson(any());
    }

    @Test
    void givenValidId_whenDeletePerson_thenReturnNothing() {
        //given
        UUID uuidMock = UuidCreator.getTimeOrderedEpoch();

        //when
        personService.deletePerson(uuidMock);

        //then
        //verify
        verify(personRepositoryPort, times(1)).deletePerson(uuidMock);
    }

    @Test
    void givenValidId_whenFindPersonById_thenReturnPerson() {
        //given
        UUID uuidMock = UuidCreator.getTimeOrderedEpoch();
        PersonRequestCreate personRequestCreate = PersonRequestCreateFactory.buildValid();
        Person person = PersonMapper.convertRequestCreateToDomain(personRequestCreate);
        Person personToCheck = Person.builder()
                .id(uuidMock)
                .name(person.getName())
                .cpf(person.getCpf())
                .email(person.getEmail())
                .phone(person.getPhone())
                .address(person.getAddress())
                .build();
        PersonResponse expectedResponse = PersonMapper.convertDomainToResponse(personToCheck);

        //when
        when(personRepositoryPort.findPersonById(uuidMock)).thenReturn(personToCheck);
        PersonResponse response = personService.findPersonById(uuidMock);

        //then
        assertNotNull(response);
        assertEquals(expectedResponse, response);

        verify(personRepositoryPort, times(1)).findPersonById(uuidMock);
    }

    @Test
    void givenPagination_whenFindAllPersons_thenReturnPersons() {
        //given
        int page = 0;
        int pageSize = 10;

        List<Person> persons = List.of(PersonMapper.convertRequestCreateToDomain(PersonRequestCreateFactory.buildValid()));
        Page<Person> personPage = new PageImpl<>(persons);

        //when
        when(personRepositoryPort.findAllPersons(page, pageSize)).thenReturn(personPage);
        Page<PersonResponse> allPersons = personService.findAllPersons(page, pageSize);

        //then
        assertNotNull(allPersons);
        assertEquals(1, allPersons.getContent().size());
        assertEquals(page, allPersons.getNumber());

        //verify
        verify(personRepositoryPort, times(1)).findAllPersons(page, pageSize);
    }

    @Test
    void givenPersonAndAddressId_whenFindPersonAddressById_thenReturnPersonAddress() {
        //given
        UUID personIdMock = UuidCreator.getTimeOrderedEpoch();
        UUID addressIdMock = UuidCreator.getTimeOrderedEpoch();
        Address address = PersonRequestCreateFactory.buildValidAddress(addressIdMock);

        when(personRepositoryPort.findAddressById(personIdMock, addressIdMock)).thenReturn(address);
        //when
        AddressResponse response = personService.findAddressById(personIdMock, addressIdMock);

        //then
        assertNotNull(response);
        assertEquals(address.getId(), response.id());
        assertEquals(address.getStreet(), response.street());

        //verify
        verify(personRepositoryPort, times(1)).findAddressById(personIdMock, addressIdMock);
    }

    @Test
    void givenValidPersonIdAndPagination_whenFindAllPersonAddresses_thenReturnPersonAddresses() {
        //given
        UUID personIdMock = UuidCreator.getTimeOrderedEpoch();
        int page = 0;
        int pageSize = 10;
        Address address = PersonRequestCreateFactory.buildValidAddress(UuidCreator.getTimeOrderedEpoch());
        Page<Address> addressPage = new PageImpl<>(List.of(address));

        when(personRepositoryPort.findAllPersonAddress(personIdMock, page, pageSize)).thenReturn(addressPage);
        //when
        Page<AddressResponse> response = personService.findAllPersonAddress(personIdMock, page, pageSize);

        //then
        assertNotNull(response);
        assertEquals(1, response.getContent().size());

        //verify
        verify(personRepositoryPort, times(1)).findAllPersonAddress(personIdMock, page, pageSize);
    }

    @Test
    void givenValidPersonIdAndAddressRequest_whenCreateNewPersonAddress_thenReturnMessageConfirmation() {
        //given
        UUID personIdMock = UuidCreator.getTimeOrderedEpoch();
        PersonRequestCreate personRequestCreate = PersonRequestCreateFactory.buildValid();
        Person person = PersonMapper.convertRequestCreateToDomain(personRequestCreate);
        AddressRequest addressRequest = PersonRequestCreateFactory.buildValidAddressRequest();

        //when
        when(personRepositoryPort.findPersonById(personIdMock)).thenReturn(person);
        MessageResponse response = personService.addNewPersonAddress(personIdMock, addressRequest);


        //then
        assertNotNull(response);
        assertEquals("Person address added", response.message());

        //verify
        verify(personRepositoryPort, times(1)).findPersonById(personIdMock);
        verify(personRepositoryPort, times(1)).savePerson(any());
    }

    @Test
    void givenValidPersonIdAndAddressIdAndAddressRequest_whenUpdatePersonAddress_thenReturnMessageConfirmation() {
        //given
        UUID personIdMock = UuidCreator.getTimeOrderedEpoch();
        UUID addressIdMock = UuidCreator.getTimeOrderedEpoch();
        PersonRequestCreate personRequestCreate = PersonRequestCreateFactory.buildValid();
        Person person = PersonMapper.convertRequestCreateToDomain(personRequestCreate);
        AddressRequest addressRequest = PersonRequestCreateFactory.buildValidAddressRequest();

        //when
        when(personRepositoryPort.findPersonById(personIdMock)).thenReturn(person);
        MessageResponse response = personService.updatePersonAddress(personIdMock, addressIdMock, addressRequest);

        //then
        assertNotNull(response);
        assertEquals("Person address updated", response.message());

        //verify
        verify(personRepositoryPort, times(1)).findPersonById(personIdMock);
        verify(personRepositoryPort, times(1)).savePerson(any());
    }

    @Test
    void givenValidPersonIdAndAddressId_whenDeletePersonAddress_thenReturnNothing() {
        //given
        UUID personIdMock = UuidCreator.getTimeOrderedEpoch();
        UUID addressIdMock = UuidCreator.getTimeOrderedEpoch();

        //when
        personService.deletePersonAddress(addressIdMock, personIdMock);

        //then
        //verify
        verify(personRepositoryPort, times(1)).deletePersonAddressById(addressIdMock, personIdMock);
    }
}