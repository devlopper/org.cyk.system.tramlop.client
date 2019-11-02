package org.cyk.system.tramlop.client.controller.entities.shipment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.cyk.system.tramlop.client.controller.entities.product.Product;
import org.cyk.utility.client.controller.component.annotation.Input;
import org.cyk.utility.client.controller.component.annotation.InputChoice;
import org.cyk.utility.client.controller.component.annotation.InputChoiceOne;
import org.cyk.utility.client.controller.component.annotation.InputChoiceOneCombo;
import org.cyk.utility.client.controller.component.annotation.InputString;
import org.cyk.utility.client.controller.component.annotation.InputStringLineOne;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Loading extends AbstractLoadingImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Input @InputChoice @InputChoiceOne @InputChoiceOneCombo
	@NotNull
	private Product product;
	
	@Input @InputString @InputStringLineOne
	@NotNull
	private String productWeight;
	
	@Input @InputChoice @InputChoiceOne @InputChoiceOneCombo
	@NotNull
	private Truck truck;
	
	@Input @InputChoice @InputChoiceOne @InputChoiceOneCombo
	@NotNull
	private Driver driver;
	
	@Input @InputString @InputStringLineOne
	@NotNull
	private String weight;
	
	@Input @InputChoice @InputChoiceOne @InputChoiceOneCombo
	@NotNull
	private Customer customer;
	
	/**/
	
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_PRODUCT_WEIGHT = "productWeight";
	public static final String FIELD_TRUCK = "truck";
	public static final String FIELD_DRIVER = "driver";
	public static final String FIELD_WEIGHT = "weight";
	public static final String FIELD_CUSTOMER = "customer";
	
}
