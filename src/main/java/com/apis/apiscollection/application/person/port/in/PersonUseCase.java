package com.apis.apiscollection.application.person.port.in;

import com.apis.apiscollection.application.address.dto.AddressRequest;
import com.apis.apiscollection.application.address.dto.AddressResponse;
import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequest;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PersonUseCase {
    MessageResponse createPerson(PersonRequest request);
    MessageResponse updatePerson(UUID id, PersonRequest request);
    void deletePerson(UUID id);
    PersonResponse findPersonById(UUID id);
    Page<PersonResponse> findAllPersons(int page, int pageSize);

    AddressResponse findAddressById(UUID personId, UUID addressId);
    MessageResponse addNewPersonAddress(UUID personId, AddressRequest addressRequest);
    MessageResponse updatePersonAddress(UUID personId, UUID addressId, AddressRequest addressRequest);
    void deletePersonAddress(UUID addressId, UUID personId);
}
