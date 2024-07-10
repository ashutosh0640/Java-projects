package com.spring_boot.crud_operation.idgenerator;

import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class StringIdGenerator implements IdentifierGenerator{

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		return UUID.randomUUID().toString();
	}

}
