package org.cyk.system.tramlop.client.controller.entities;
import org.cyk.system.tramlop.server.representation.entities.DriverDto;
import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class DriverMapper extends AbstractMapperSourceDestinationImpl<Driver, DriverDto> {
	private static final long serialVersionUID = 1L;
    	
}