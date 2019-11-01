package org.cyk.system.tramlop.client.controller.entities.shipment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.cyk.utility.client.controller.component.annotation.Input;
import org.cyk.utility.client.controller.component.annotation.InputString;
import org.cyk.utility.client.controller.component.annotation.InputStringLineOne;
import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringAndIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public abstract class AbstractPersonImpl extends AbstractDataIdentifiableSystemStringAndIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Input @InputString @InputStringLineOne
	@NotNull
	private String firstName;
	
	@Input @InputString @InputStringLineOne
	private String lastNames;
	
	@Input @InputString @InputStringLineOne
	private String identityCard;
	
	@Input @InputString @InputStringLineOne
	private String phoneNumber;
	
	@Input @InputString @InputStringLineOne
	private String electronicMailAddress;
	
}
