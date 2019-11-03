package org.cyk.system.tramlop.client.controller.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;
import org.cyk.utility.__kernel__.system.action.SystemActionFieldsNamesGetter;
import org.cyk.utility.client.controller.component.menu.MenuBuilderMapGetter;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		__inject__(org.cyk.system.tramlop.client.controller.api.ApplicationScopeLifeCycleListener.class).initialize(null);
		__setQualifierClassTo__(org.cyk.system.tramlop.server.annotation.System.class, MenuBuilderMapGetter.class,SystemActionFieldsNamesGetter.class);
	}
	
	@Override
	public void __destroy__(Object object) {}
	
	/**/
}
