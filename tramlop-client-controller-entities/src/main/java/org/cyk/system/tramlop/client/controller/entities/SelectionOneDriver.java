package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;

import org.cyk.utility.__kernel__.instance.SelectionOne;
import org.cyk.utility.__kernel__.properties.Properties;

public class SelectionOneDriver extends SelectionOne<Driver> implements Serializable {
	private static final long serialVersionUID = 1L;

	public SelectionOneDriver(Properties properties) {
		super(Driver.class,properties);
	}
	
	public SelectionOneDriver() {
		super(Driver.class);
	}
}
