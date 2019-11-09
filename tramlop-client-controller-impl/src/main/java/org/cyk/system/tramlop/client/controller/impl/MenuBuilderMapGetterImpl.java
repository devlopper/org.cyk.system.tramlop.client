package org.cyk.system.tramlop.client.controller.impl;

import java.io.Serializable;
import java.security.Principal;

import org.cyk.system.tramlop.client.controller.entities.Customer;
import org.cyk.system.tramlop.client.controller.entities.Driver;
import org.cyk.system.tramlop.client.controller.entities.Path;
import org.cyk.system.tramlop.client.controller.entities.Place;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.system.tramlop.client.controller.entities.Truck;
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
				__inject__(MenuItemBuilder.class).setCommandableName("Contrat")
				//.list(Shipment.class)
				,__inject__(MenuItemBuilder.class).setCommandableName("Livraison")
				//.list(Product.class)
				,__inject__(MenuItemBuilder.class).setCommandableName("Parametrage")
				.list(Product.class,Truck.class,Driver.class,Place.class,Path.class,Customer.class)
				);
	}

	

}
