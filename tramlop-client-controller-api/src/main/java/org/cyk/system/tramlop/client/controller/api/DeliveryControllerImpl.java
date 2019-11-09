package org.cyk.system.tramlop.client.controller.api;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class DeliveryControllerImpl extends AbstractControllerEntityImpl<Delivery> implements DeliveryController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
