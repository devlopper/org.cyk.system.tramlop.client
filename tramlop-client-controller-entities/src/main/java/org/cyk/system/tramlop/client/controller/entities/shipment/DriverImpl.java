package org.cyk.system.tramlop.client.controller.entities.shipment;

import java.io.Serializable;

import org.cyk.utility.client.controller.data.AbstractDataIdentifiedByStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class DriverImpl extends AbstractDataIdentifiedByStringImpl implements Driver,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter @Accessors(chain=true)
	private String names;
	
}
