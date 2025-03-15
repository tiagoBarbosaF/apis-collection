package com.apis.apiscollection.application.person.port.in;

import com.apis.apiscollection.application.person.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequest;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import org.springframework.data.domain.Page;

public interface PersonUseCase {
    MessageResponse createPerson(PersonRequest request);
    MessageResponse updatePerson(long id, PersonRequest request);
    void deletePerson(long id);
    PersonResponse findPersonById(long id);
    Page<PersonResponse> findAllPersons(int page, int pageSize);
}
