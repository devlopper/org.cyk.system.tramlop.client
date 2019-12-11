package org.cyk.system.tramlop.client.controller.entities;
import org.cyk.system.tramlop.server.representation.entities.TaskDto;
import org.cyk.utility.__kernel__.mapping.AbstractMapperSourceDestinationImpl;
import org.mapstruct.Mapper;

@Mapper
public abstract class TaskMapper extends AbstractMapperSourceDestinationImpl<Task, TaskDto> {
	private static final long serialVersionUID = 1L;
    	
}