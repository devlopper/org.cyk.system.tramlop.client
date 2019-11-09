package org.cyk.system.tramlop.client.controller.entities;
import org.cyk.system.tramlop.server.representation.entities.AgreementDto;
import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class AgreementMapper extends AbstractMapperSourceDestinationImpl<Agreement, AgreementDto> {
	private static final long serialVersionUID = 1L;
    	
}