package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Driver extends AbstractPersonImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<Agreement> agreements;
	private ArrayList<Truck> trucks;
	
	public static final String FIELD_AGREEMENTS = "agreements";
	public static final String FIELD_TRUCKS = "trucks";
	
}
