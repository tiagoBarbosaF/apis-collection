package com.apis.apiscollection.application.person.port.in;

import com.apis.apiscollection.application.address.dto.AddressRequest;
import com.apis.apiscollection.application.address.dto.AddressResponse;
import com.apis.apiscollection.application.person.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequest;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import org.springframework.data.domain.Page;

public interface PersonUseCase {
    MessageResponse createPerson(PersonRequest request);
    MessageResponse updatePerson(Long id, PersonRequest request);
    void deletePerson(Long id);
    PersonResponse findPersonById(Long id);
    Page<PersonResponse> findAllPersons(int page, int pageSize);

    AddressResponse findAddressById(Long personId, Long addressId);
    MessageResponse addNewPersonAddress(Long personId, AddressRequest addressRequest);
    MessageResponse updatePersonAddress(Long personId, Long addressId, AddressRequest addressRequest);
    void deletePersonAddress(Long addressId, Long personId);
}
