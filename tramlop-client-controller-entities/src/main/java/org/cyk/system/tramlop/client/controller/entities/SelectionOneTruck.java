package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;

import org.cyk.utility.__kernel__.instance.SelectionOne;
import org.cyk.utility.__kernel__.properties.Properties;

public class SelectionOneTruck extends SelectionOne<Truck> implements Serializable {
	private static final long serialVersionUID = 1L;

	public SelectionOneTruck(Properties properties) {
		super(Truck.class,properties);
	}
	
	public SelectionOneTruck() {
		super(Truck.class);
	}
}
