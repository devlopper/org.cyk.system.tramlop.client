package org.cyk.system.tramlop.client.controller.api.product;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.product.Product;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class ProductControllerImpl extends AbstractControllerEntityImpl<Product> implements ProductController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
