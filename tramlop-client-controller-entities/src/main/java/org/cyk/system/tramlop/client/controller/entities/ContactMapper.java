package org.cyk.system.tramlop.client.controller.entities;
import org.cyk.utility.__kernel__.object.__static__.representation.ContactDto;
import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class ContactMapper extends AbstractMapperSourceDestinationImpl<Contact, ContactDto> {
	private static final long serialVersionUID = 1L;
    	
}