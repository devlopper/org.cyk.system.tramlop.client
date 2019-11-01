package org.cyk.system.tramlop.client.controller.entities.shipment;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Customer extends AbstractPersonImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
}
