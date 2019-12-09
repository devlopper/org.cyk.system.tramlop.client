package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;
import java.util.List;

import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
//@Accessors(chain=true) TODO JSF does not support chaining pattern
public class Truck extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Agreement agreement;
	private Driver driver;
	private List<Driver> drivers;
	private Integer weightInKiloGramOfProductAfterLoad;
	private Integer weightInKiloGramOfProductAfterUnload;
	private Integer weightInKiloGramOfProductLost;
	private Product product;
	
	public Truck setDriverFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.driver = null;
		else
			this.driver = InstanceGetter.getInstance().getByBusinessIdentifier(Driver.class, code);
		return this;
	}
	
	public static final String FIELD_TASKS = "tasks";
	public static final String FIELD_TASKS_COUNTS = "tasksCounts";
	public static final String FIELD_DRIVER = "driver";
	public static final String FIELD_DRIVERS = "drivers";
	public static final String FIELD_PRODUCT = "product";
	public static final String FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_LOAD = "weightInKiloGramOfProductAfterLoad";
	public static final String FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_UNLOAD = "weightInKiloGramOfProductAfterUnload";
	public static final String FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_LOST = "weightInKiloGramOfProductLost";
}
