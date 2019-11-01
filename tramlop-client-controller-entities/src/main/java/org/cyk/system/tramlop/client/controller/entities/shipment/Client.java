package org.cyk.system.tramlop.client.controller.entities.shipment;

import java.io.Serializable;

import org.cyk.utility.client.controller.component.annotation.Input;
import org.cyk.utility.client.controller.component.annotation.InputString;
import org.cyk.utility.client.controller.component.annotation.InputStringLineOne;
import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringAndIdentifiableBusinessStringImpl;
import org.cyk.utility.client.controller.data.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Client extends AbstractDataIdentifiableSystemStringAndIdentifiableBusinessStringImpl implements Data,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Input @InputString @InputStringLineOne
	private String firstNameAndLastNames;
	
	@Input @InputString @InputStringLineOne
	private String phoneNumber;
	
	@Input @InputString @InputStringLineOne
	private String electronicMailAddress;
	
}
