package org.cyk.system.tramlop.client.controller.entities;
import org.cyk.system.tramlop.server.representation.entities.PathDto;
import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class PathMapper extends AbstractMapperSourceDestinationImpl<Path, PathDto> {
	private static final long serialVersionUID = 1L;
    	
}