package org.cyk.system.tramlop.client.controller.api;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.Employee;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class EmployeeControllerImpl extends AbstractControllerEntityImpl<Employee> implements EmployeeController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
