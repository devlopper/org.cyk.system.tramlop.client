package org.cyk.system.tramlop.client.controller.api.shipment;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.shipment.Unloading;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class UnloadingControllerImpl extends AbstractControllerEntityImpl<Unloading> implements UnloadingController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
