package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;

import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringNamableImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Task extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Weighing weighing;
	private Integer weightInKiloGram;
	private Product product;
	private Driver driver;
	private Place unloadingPlace;
	private Existence existence;
	private Integer orderNumber;
	
	public static final String FIELD_EXISTENCE = "existence";
	public static final String FIELD_WEIGHT_IN_KILO_GRAM = "weightInKiloGram";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_DRIVER = "driver";
	public static final String FIELD_UNLOADING_PLACE = "unloadingPlace";
	public static final String FIELD_DELIVERIES = "deliveries";
}
