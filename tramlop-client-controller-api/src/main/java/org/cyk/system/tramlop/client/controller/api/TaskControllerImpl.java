package org.cyk.system.tramlop.client.controller.api;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.Task;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class TaskControllerImpl extends AbstractControllerEntityImpl<Task> implements TaskController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
