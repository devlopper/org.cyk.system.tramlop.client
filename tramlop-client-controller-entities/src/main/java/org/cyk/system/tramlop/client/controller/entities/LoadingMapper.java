package org.cyk.system.tramlop.client.controller.entities;
import org.cyk.system.tramlop.server.representation.entities.LoadingDto;
import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class LoadingMapper extends AbstractMapperSourceDestinationImpl<Loading, LoadingDto> {
	private static final long serialVersionUID = 1L;
    	
}