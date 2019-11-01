package org.cyk.system.tramlop.client.controller.api.shipment;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.shipment.Shipment;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class ShipmentControllerImpl extends AbstractControllerEntityImpl<Shipment> implements ShipmentController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
