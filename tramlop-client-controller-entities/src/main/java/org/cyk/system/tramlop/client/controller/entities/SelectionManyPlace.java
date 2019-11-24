package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;

import org.cyk.utility.__kernel__.instance.SelectionMany;
import org.cyk.utility.__kernel__.properties.Properties;

public class SelectionManyPlace extends SelectionMany<Place> implements Serializable {
	private static final long serialVersionUID = 1L;

	public SelectionManyPlace(Properties properties) {
		super(Place.class,properties);
	}
	
	public SelectionManyPlace() {
		super(Place.class);
	}
}
