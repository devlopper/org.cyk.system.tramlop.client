package org.cyk.system.tramlop.client.controller.impl;

import java.io.Serializable;
import java.security.Principal;

import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.utility.__kernel__.klass.PersistableClassesGetter;
import org.cyk.utility.client.controller.component.menu.AbstractMenuBuilderMapInstantiatorImpl;
import org.cyk.utility.client.controller.component.menu.MenuBuilder;
import org.cyk.utility.client.controller.component.menu.MenuItemBuilder;

@org.cyk.system.tramlop.server.annotation.System
public class MenuBuilderMapGetterImpl extends AbstractMenuBuilderMapInstantiatorImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __instantiateSessionMenuBuilderItems__(Object key, MenuBuilder sessionMenuBuilder, Object request,Principal principal) {
		sessionMenuBuilder.addItems(
				__inject__(MenuItemBuilder.class).setCommandableName("Contrat")
					.list(Agreement.class)
				,__inject__(MenuItemBuilder.class).setCommandableName("Livraison")
					.list(Delivery.class)
				,__inject__(MenuItemBuilder.class).setCommandableName("Parametrage")
					.list(PersistableClassesGetter.getInstance().get())
				);	
	}

}
