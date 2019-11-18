package org.cyk.system.tramlop.client.controller.entities;
import org.cyk.system.tramlop.server.representation.entities.AgreementTruckDto;
import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class AgreementTruckMapper extends AbstractMapperSourceDestinationImpl<AgreementTruck, AgreementTruckDto> {
	private static final long serialVersionUID = 1L;
    	
}