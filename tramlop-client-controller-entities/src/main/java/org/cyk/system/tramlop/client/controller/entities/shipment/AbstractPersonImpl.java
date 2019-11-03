package org.cyk.system.tramlop.client.controller.entities.shipment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.cyk.utility.client.controller.component.annotation.Input;
import org.cyk.utility.client.controller.component.annotation.InputString;
import org.cyk.utility.client.controller.component.annotation.InputStringLineOne;
import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public abstract class AbstractPersonImpl extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl implements Serializable {
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
	
	/**/
	
	public static final String FIELD_FIRST_NAME = "firstName";
	public static final String FIELD_LAST_NAMES = "lastNames";
	public static final String FIELD_IDENTITY_CARD = "identityCard";
	public static final String FIELD_PHONE_NUMBER = "phoneNumber";
	public static final String FIELD_ELECTRONIC_MAIL_ADDRESS = "electronicMailAddress";
}
