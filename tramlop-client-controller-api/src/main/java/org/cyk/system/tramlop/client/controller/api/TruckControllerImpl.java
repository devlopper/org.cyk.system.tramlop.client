package org.cyk.system.tramlop.client.controller.api;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.Truck;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class TruckControllerImpl extends AbstractControllerEntityImpl<Truck> implements TruckController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
