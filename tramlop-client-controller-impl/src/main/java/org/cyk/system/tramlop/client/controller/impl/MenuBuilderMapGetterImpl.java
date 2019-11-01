package org.cyk.system.tramlop.client.controller.impl;

import java.io.Serializable;
import java.security.Principal;

import org.cyk.system.tramlop.client.controller.entities.product.Product;
import org.cyk.system.tramlop.client.controller.entities.shipment.Customer;
import org.cyk.system.tramlop.client.controller.entities.shipment.Driver;
import org.cyk.system.tramlop.client.controller.entities.shipment.Shipment;
import org.cyk.system.tramlop.client.controller.entities.shipment.Truck;
import org.cyk.utility.client.controller.component.menu.AbstractMenuBuilderMapGetterImpl;
import org.cyk.utility.client.controller.component.menu.MenuBuilder;
import org.cyk.utility.client.controller.component.menu.MenuItemBuilder;

@org.cyk.system.tramlop.server.annotation.System
public class MenuBuilderMapGetterImpl extends AbstractMenuBuilderMapGetterImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void ____executePrincipalIsNotNull____(MenuBuilder sessionMenuBuilder, Object request, Principal principal) throws Exception {
		
	}

	@Override
	protected void ____executePrincipalIsNull____(MenuBuilder sessionMenuBuilder, Object request) throws Exception {
		sessionMenuBuilder.addItems(
				__inject__(MenuItemBuilder.class).setCommandableName("Chargement")
				.list(Shipment.class)
				,__inject__(MenuItemBuilder.class).setCommandableName("Stockage")
				.list(Product.class)
				,__inject__(MenuItemBuilder.class).setCommandableName("Parametrage")
				.list(Driver.class,Truck.class,Customer.class)
				);
	}

	

}
