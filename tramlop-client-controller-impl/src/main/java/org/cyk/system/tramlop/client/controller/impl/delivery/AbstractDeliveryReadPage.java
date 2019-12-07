package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;

import org.cyk.system.tramlop.client.controller.api.DeliveryController;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.omnifaces.util.Faces;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AbstractDeliveryReadPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Delivery delivery;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		delivery = __inject__(DeliveryController.class).readBySystemIdentifier(Faces.getRequestParameter("entityidentifier"),new Properties().setFields("tasks"));
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Livraison N° "+delivery.getCode();
	}
	
}
