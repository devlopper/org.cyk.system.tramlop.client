package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringNamableImpl;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter //@Accessors(chain=true)
public class Product extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal lossRate;
	private Integer weightInKiloGram;
	
	public static final String FIELD_LOSS_RATE = "lossRate";
	public static final String FIELD_WEIGHT_IN_KILO_GRAM = "weightInKiloGram";
}
