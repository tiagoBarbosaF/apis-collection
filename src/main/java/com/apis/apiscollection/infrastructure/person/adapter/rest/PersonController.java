package com.apis.apiscollection.infrastructure.person.adapter.rest;

import com.apis.apiscollection.application.address.dto.AddressRequest;
import com.apis.apiscollection.application.address.dto.AddressResponse;
import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequest;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import com.apis.apiscollection.application.person.port.in.PersonUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@Tag(name = "Person", description = "Manages people-related operations")
public class PersonController {
    private final PersonUseCase personUseCase;

    public PersonController(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new person", description = "Creates a new person and returns the create person message.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<MessageResponse> create(@RequestBody @Valid PersonRequest request) {
        MessageResponse response = personUseCase.createPerson(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    @Operation(summary = "Update person", description = "Update a person using id and new information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body or id.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<MessageResponse> update(@RequestParam Long id,
                                                  @RequestBody @Valid PersonRequest request) {
        MessageResponse response = personUseCase.updatePerson(id, request);
        return response != null
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/id")
    @Operation(summary = "Get a person by ID", description = "Find a person using the ID reference.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<PersonResponse> getPerson(@RequestParam long id) {
        PersonResponse response = personUseCase.findPersonById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list")
    @Operation(summary = "List all persons", description = "List all person, paginated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<Page<PersonResponse>> findAllPersons(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int pageSize) {
        Page<PersonResponse> response = personUseCase.findAllPersons(page, pageSize);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(response)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping
    @Operation(summary = "Delete person", description = "Delete person by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<Void> deletePerson(@RequestParam long id) {
        personUseCase.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/address/id")
    @Operation(summary = "Get an address", description = "Find an address using the ID reference and person ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<AddressResponse> getAddressById(@RequestParam(name = "person_id") long person_id,
                                                          @RequestParam(name = "address_id") long address_id) {
        AddressResponse response = personUseCase.findAddressById(person_id, address_id);
        return response != null
                ? ResponseEntity.status(HttpStatus.OK).body(response)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/address")
    @Operation(summary = "Add a new person address", description = "Add a new a person address using id and new information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body or id.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<MessageResponse> addNewPersonAddress(@RequestParam(name = "person_id") Long person_id,
                                                               @RequestBody @Valid AddressRequest request) {
        MessageResponse response = personUseCase.addNewPersonAddress(person_id, request);
        return response != null
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/address")
    @Operation(summary = "Update person address", description = "Update a person address using id and new information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body or id.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<MessageResponse> updatePersonAddress(@RequestParam(name = "person_id") Long person_id,
                                                               @RequestParam(name = "address_id") Long address_id,
                                                               @RequestBody @Valid AddressRequest request) {
        MessageResponse response = personUseCase.updatePersonAddress(person_id, address_id, request);
        return response != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("address")
    @Operation(summary = "Delete person address", description = "Delete person address by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<Void> deletePersonAddress(@RequestParam(name = "address_id") Long address_id,
                                                    @RequestParam(name = "person_id") Long person_id) {
        personUseCase.deletePersonAddress(address_id, person_id);
        return ResponseEntity.noContent().build();
    }
}
