package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;

import org.cyk.utility.__kernel__.instance.SelectionMany;
import org.cyk.utility.__kernel__.properties.Properties;

public class SelectionManyProduct extends SelectionMany<Product> implements Serializable {
	private static final long serialVersionUID = 1L;

	public SelectionManyProduct(Properties properties) {
		super(Product.class,properties);
	}
	
	public SelectionManyProduct() {
		super(Product.class);
	}
}
