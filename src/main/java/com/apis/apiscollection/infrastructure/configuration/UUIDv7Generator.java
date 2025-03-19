package com.apis.apiscollection.infrastructure.configuration;

import com.github.f4b6a3.uuid.UuidCreator;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.EventType;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.factory.spi.StandardGenerator;

import java.util.EnumSet;

public class UUIDv7Generator implements IdentifierGenerator, StandardGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return UuidCreator.getTimeOrdered();
    }

    @Override
    public void initialize(SqlStringGenerationContext context) {
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }
}
