package org.cyk.system.tramlop.client.controller.api;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.Place;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class PlaceControllerImpl extends AbstractControllerEntityImpl<Place> implements PlaceController,Serializable {
	private static final long serialVersionUID = 1L;
	
}