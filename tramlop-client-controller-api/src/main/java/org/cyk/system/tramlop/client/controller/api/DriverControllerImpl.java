package org.cyk.system.tramlop.client.controller.api;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.Driver;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class DriverControllerImpl extends AbstractControllerEntityImpl<Driver> implements DriverController,Serializable {
	private static final long serialVersionUID = 1L;
	
}