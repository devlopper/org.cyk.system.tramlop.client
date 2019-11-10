package org.cyk.system.tramlop.client.controller.entities;
import org.cyk.utility.__kernel__.object.__static__.representation.PersonDto;
import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class PersonMapper extends AbstractMapperSourceDestinationImpl<Person, PersonDto> {
	private static final long serialVersionUID = 1L;
    	
}