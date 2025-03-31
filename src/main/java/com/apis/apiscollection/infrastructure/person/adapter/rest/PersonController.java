package com.apis.apiscollection.infrastructure.person.adapter.rest;

import com.apis.apiscollection.application.address.dto.AddressRequest;
import com.apis.apiscollection.application.address.dto.AddressResponse;
import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequestCreate;
import com.apis.apiscollection.application.person.dto.PersonRequestUpdate;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.application.person.port.in.PersonUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PersonController implements PersonApi {
    private final PersonUseCase personUseCase;

    public PersonController(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @Override
    public ResponseEntity<MessageResponse> create(@RequestBody @Valid PersonRequestCreate request) {
        MessageResponse response = personUseCase.createPerson(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<MessageResponse> update(@RequestParam UUID id,
                                                  @RequestBody @Valid PersonRequestUpdate request) {
        MessageResponse response = personUseCase.updatePerson(id, request);
        return response != null
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @Override
    public ResponseEntity<PersonResponse> getPerson(@RequestParam UUID id) {
        PersonResponse response = personUseCase.findPersonById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Page<PersonResponse>> findAllPersons(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int pageSize) {
        Page<PersonResponse> response = personUseCase.findAllPersons(page, pageSize);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(response)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public ResponseEntity<Void> deletePerson(@RequestParam UUID id) {
        personUseCase.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<AddressResponse> getAddressById(@RequestParam(name = "person_id") UUID person_id,
                                                          @RequestParam(name = "address_id") UUID address_id) {
        AddressResponse response = personUseCase.findAddressById(person_id, address_id);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(response)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public ResponseEntity<Page<AddressResponse>> findAllPersonAddress(@RequestParam(name = "person_id") UUID person_id,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int pageSize) {
        Page<AddressResponse> response = personUseCase.findAllPersonAddress(person_id, page, pageSize);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(response)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public ResponseEntity<MessageResponse> addNewPersonAddress(@RequestParam(name = "person_id") UUID person_id,
                                                               @RequestBody @Valid AddressRequest request) {
        MessageResponse response = personUseCase.addNewPersonAddress(person_id, request);
        return response != null
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @Override
    public ResponseEntity<MessageResponse> updatePersonAddress(@RequestParam(name = "person_id") UUID person_id,
                                                               @RequestParam(name = "address_id") UUID address_id,
                                                               @RequestBody @Valid AddressRequest request) {
        MessageResponse response = personUseCase.updatePersonAddress(person_id, address_id, request);
        return response != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @Override
    public ResponseEntity<Void> deletePersonAddress(@RequestParam(name = "address_id") UUID address_id,
                                                    @RequestParam(name = "person_id") UUID person_id) {
        personUseCase.deletePersonAddress(address_id, person_id);
        return ResponseEntity.noContent().build();
    }
}
