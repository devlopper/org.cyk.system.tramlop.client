package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringNamableImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Place extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringNamableImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal longitude;
	private BigDecimal latitude;
	
	public static final String FIELD_LONGITUDE = "longitude";
	public static final String FIELD_LATITUDE = "latitude";
	
}
