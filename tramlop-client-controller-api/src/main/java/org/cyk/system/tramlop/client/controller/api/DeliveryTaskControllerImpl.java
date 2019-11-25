package org.cyk.system.tramlop.client.controller.api;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.DeliveryTask;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class DeliveryTaskControllerImpl extends AbstractControllerEntityImpl<DeliveryTask> implements DeliveryTaskController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
