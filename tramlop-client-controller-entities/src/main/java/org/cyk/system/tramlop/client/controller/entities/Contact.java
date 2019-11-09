package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;

import org.cyk.utility.client.controller.component.annotation.Input;
import org.cyk.utility.client.controller.component.annotation.InputString;
import org.cyk.utility.client.controller.component.annotation.InputStringLineOne;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Input @InputString @InputStringLineOne private String phoneNumber;
	@Input @InputString @InputStringLineOne private String electronicMailAddress;
	
	public static final String FIELD_PHONE_NUMBER = "phoneNumber";
	public static final String FIELD_ELECTRONIC_MAIL_ADDRESS = "electronicMailAddress";
}
