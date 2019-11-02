package org.cyk.system.tramlop.client.controller.entities.product;

import java.io.Serializable;

import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringNamableImpl;
import org.cyk.utility.client.controller.data.Data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Product extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringNamableImpl implements Data,Serializable {
	private static final long serialVersionUID = 1L;
	
}
