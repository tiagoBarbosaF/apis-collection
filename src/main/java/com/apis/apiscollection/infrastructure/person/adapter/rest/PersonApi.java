package com.apis.apiscollection.infrastructure.person.adapter.rest;

import com.apis.apiscollection.application.address.dto.AddressRequest;
import com.apis.apiscollection.application.address.dto.AddressResponse;
import com.apis.apiscollection.application.dto.MessageResponse;
import com.apis.apiscollection.application.person.dto.PersonRequestCreate;
import com.apis.apiscollection.application.person.dto.PersonRequestUpdate;
import com.apis.apiscollection.application.person.dto.PersonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/person")
@Tag(name = "Person", description = "Manages people-related operations")
public interface PersonApi {

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
    ResponseEntity<MessageResponse> create(@RequestBody @Valid PersonRequestCreate request);

    @PutMapping
    @Operation(summary = "Update person", description = "Update a person using id and new information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body or id.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<MessageResponse> update(@RequestParam UUID id,
                                           @RequestBody @Valid PersonRequestUpdate request);

    @GetMapping("/id")
    @Operation(summary = "Get a person by ID", description = "Find a person using the ID reference.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<PersonResponse> getPerson(@RequestParam UUID id);

    @GetMapping("/list")
    @Operation(summary = "List all persons", description = "List all person, paginated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<Page<PersonResponse>> findAllPersons(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int pageSize);

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
    ResponseEntity<Void> deletePerson(@RequestParam UUID id);

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
    ResponseEntity<AddressResponse> getAddressById(@RequestParam(name = "person_id") UUID person_id,
                                                   @RequestParam(name = "address_id") UUID address_id);

    @GetMapping("/address/list")
    @Operation(summary = "List all person addresses", description = "List all person addresses, paginated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<Page<AddressResponse>> findAllPersonAddress(@RequestParam(name = "person_id") UUID person_id,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int pageSize);

    @PostMapping("/address")
    @Operation(summary = "Add a new person address", description = "Add a new a person address using id and new information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body or id.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json")),
    })
    ResponseEntity<MessageResponse> addNewPersonAddress(@RequestParam(name = "person_id") UUID person_id,
                                                        @RequestBody @Valid AddressRequest request);

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
    ResponseEntity<MessageResponse> updatePersonAddress(@RequestParam(name = "person_id") UUID person_id,
                                                        @RequestParam(name = "address_id") UUID address_id,
                                                        @RequestBody @Valid AddressRequest request);

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
    ResponseEntity<Void> deletePersonAddress(@RequestParam(name = "address_id") UUID address_id,
                                             @RequestParam(name = "person_id") UUID person_id);
}
