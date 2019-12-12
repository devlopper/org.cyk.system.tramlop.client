package org.cyk.system.tramlop.client.controller.impl;

import java.io.Serializable;
import java.security.Principal;

import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.utility.__kernel__.icon.Icon;
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
				__inject__(MenuItemBuilder.class).setCommandableName("Contrat").setCommandableIcon(Icon.FILE).addChild(
						__inject__(MenuItemBuilder.class).setCommandableName("Contrats en cours").setCommandableNavigationIdentifier("agreementListWhereClosedIsFalseView")
						,__inject__(MenuItemBuilder.class).setCommandableName("Contrats clos").setCommandableNavigationIdentifier("agreementListWhereClosedIsTrueView")
						//,__inject__(MenuItemBuilder.class).setCommandableName("Nouveau contrat").setCommandableNavigationIdentifier("agreementCreateView")
						.setCommandableIcon(Icon.PLUS)
						).list(Agreement.class)
				,__inject__(MenuItemBuilder.class).setCommandableName("Livraison").setCommandableIcon(Icon.TRUCK)
					.addChild(
							__inject__(MenuItemBuilder.class).setCommandableName("Livraisons en cours").setCommandableNavigationIdentifier("deliveryListWhereClosedIsFalseView")
							,__inject__(MenuItemBuilder.class).setCommandableName("Livraisons terminées").setCommandableNavigationIdentifier("deliveryListWhereClosedIsTrueView")
							,__inject__(MenuItemBuilder.class).setCommandableName("Peser avant chargement").setCommandableNavigationIdentifier("deliveryWeighBeforeLoadView")
							,__inject__(MenuItemBuilder.class).setCommandableName("Charger").setCommandableNavigationIdentifier("deliveryLoadView")
							,__inject__(MenuItemBuilder.class).setCommandableName("Peser après chargement").setCommandableNavigationIdentifier("deliveryWeighAfterLoadView")
							,__inject__(MenuItemBuilder.class).setCommandableName("Peser avant déchargement").setCommandableNavigationIdentifier("deliveryWeighBeforeUnloadView")
							,__inject__(MenuItemBuilder.class).setCommandableName("Peser après déchargement").setCommandableNavigationIdentifier("deliveryWeighAfterUnloadView")
							)
				,__inject__(MenuItemBuilder.class).setCommandableName("Parametrage").setCommandableIcon(Icon.GEARS)
					.list(PersistableClassesGetter.getInstance().get())
				);	
	}

}
