package org.cyk.system.tramlop.client.controller.api;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.IncidentType;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class IncidentTypeControllerImpl extends AbstractControllerEntityImpl<IncidentType> implements IncidentTypeController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
