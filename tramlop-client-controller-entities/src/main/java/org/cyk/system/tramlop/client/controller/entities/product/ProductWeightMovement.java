package org.cyk.system.tramlop.client.controller.entities.product;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.cyk.utility.client.controller.component.annotation.Input;
import org.cyk.utility.client.controller.component.annotation.InputChoice;
import org.cyk.utility.client.controller.component.annotation.InputChoiceOne;
import org.cyk.utility.client.controller.component.annotation.InputChoiceOneCombo;
import org.cyk.utility.client.controller.component.annotation.InputString;
import org.cyk.utility.client.controller.component.annotation.InputStringLineOne;
import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl;
import org.cyk.utility.client.controller.data.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class ProductWeightMovement extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl implements Data,Serializable {
	private static final long serialVersionUID = 1L;

	@Input @InputChoice @InputChoiceOne @InputChoiceOneCombo
	@NotNull
	private Product product;
	
	@Input @InputString @InputStringLineOne
	@NotNull
	private String weight;

	/**/
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_WEIGHT = "weight";
}
