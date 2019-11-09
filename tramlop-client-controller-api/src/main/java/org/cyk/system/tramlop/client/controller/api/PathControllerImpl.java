package org.cyk.system.tramlop.client.controller.api;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.Path;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class PathControllerImpl extends AbstractControllerEntityImpl<Path> implements PathController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
