package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;

import org.cyk.utility.client.controller.component.annotation.Input;
import org.cyk.utility.client.controller.component.annotation.InputBoolean;
import org.cyk.utility.client.controller.component.annotation.InputBooleanCheckBox;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Employee extends AbstractPersonImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Input @InputBoolean @InputBooleanCheckBox
	private Boolean notifiableOnDeliveryDurationAlert;

	public static final String FIELD_NOTIFIABLE_ON_DELIVERY_DURATION_ALERT = "notifiableOnDeliveryDurationAlert";
}
