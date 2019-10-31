package org.cyk.system.tramlop.client.controller.entities.shipment;

import org.cyk.utility.client.controller.data.DataIdentifiedByString;

public interface Driver extends DataIdentifiedByString {

	String getNames();
	Driver setNames(String names);
	
}
