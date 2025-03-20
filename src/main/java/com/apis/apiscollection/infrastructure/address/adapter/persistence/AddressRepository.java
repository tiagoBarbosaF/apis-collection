package com.apis.apiscollection.infrastructure.address.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {

    Optional<AddressEntity> findByIdAndPersonId(UUID addressId, UUID personId);

    void deleteByIdAndPersonId(UUID id, UUID personId);
}
