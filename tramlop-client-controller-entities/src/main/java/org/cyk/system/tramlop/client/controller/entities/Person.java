package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.cyk.utility.client.controller.component.annotation.Input;
import org.cyk.utility.client.controller.component.annotation.InputString;
import org.cyk.utility.client.controller.component.annotation.InputStringLineOne;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull @Input @InputString @InputStringLineOne private String firstName;
	@Input @InputString @InputStringLineOne private String lastNames;
	@Input @InputString @InputStringLineOne private String identityCard;
	private String names;
	private Contact contact;
	
	public Contact getContact(Boolean injectIfNull) {
		if(contact == null && Boolean.TRUE.equals(injectIfNull))
			contact = new Contact();
		return contact;
	}
	
	public static final String FIELD_FIRST_NAME = "firstName";
	public static final String FIELD_LAST_NAMES = "lastNames";
	public static final String FIELD_NAMES = "names";
	public static final String FIELD_IDENTITY_CARD = "identityCard";
	public static final String FIELD_CONTACT = "contact";
}
