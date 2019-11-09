package org.cyk.system.tramlop.client.controller.entities;
import org.cyk.system.tramlop.server.representation.entities.CustomerDto;
import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class CustomerMapper extends AbstractMapperSourceDestinationImpl<Customer, CustomerDto> {
	private static final long serialVersionUID = 1L;
    	
}