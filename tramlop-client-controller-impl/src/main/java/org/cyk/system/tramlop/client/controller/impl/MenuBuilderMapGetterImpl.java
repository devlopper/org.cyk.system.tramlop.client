package org.cyk.system.tramlop.client.controller.impl;

import java.io.Serializable;
import java.security.Principal;

import org.cyk.system.tramlop.client.controller.entities.shipment.Driver;
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
				/*.addChild(
						__inject__(MenuItemBuilder.class).setCommandableName("Charger")
						,__inject__(MenuItemBuilder.class).setCommandableName("Décharger")
						,__inject__(MenuItemBuilder.class).setCommandableName("Chargements")
						,__inject__(MenuItemBuilder.class).setCommandableName("Chauffeurs")
						,__inject__(MenuItemBuilder.class).setCommandableName("Camions")
						)
				*/
				.list(Driver.class)
				,__inject__(MenuItemBuilder.class).setCommandableName("Stockage")
				.addChild(
						__inject__(MenuItemBuilder.class).setCommandableName("Stocker")
						,__inject__(MenuItemBuilder.class).setCommandableName("Déstocker")
						,__inject__(MenuItemBuilder.class).setCommandableName("Produits")
						)
				);
	}

	

}
