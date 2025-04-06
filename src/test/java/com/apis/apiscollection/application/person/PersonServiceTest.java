package com.apis.apiscollection.application.person;

import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequestCreate;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.application.person.mapper.PersonMapper;
import com.apis.apiscollection.application.person.port.out.PersonRepositoryPort;
import com.apis.apiscollection.domain.person.Person;
import com.apis.apiscollection.util.PersonRequestCreateFactory;
import com.github.f4b6a3.uuid.UuidCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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
}