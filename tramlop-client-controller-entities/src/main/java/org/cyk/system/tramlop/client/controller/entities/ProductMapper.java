package org.cyk.system.tramlop.client.controller.entities;
import org.cyk.system.tramlop.server.representation.entities.ProductDto;
import org.cyk.utility.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class ProductMapper extends AbstractMapperSourceDestinationImpl<Product, ProductDto> {
	private static final long serialVersionUID = 1L;
    	
}