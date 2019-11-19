package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.DeliveryController;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryBoardPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Collection<Delivery> runningDeliveries,closedDeliveries;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		runningDeliveries = __inject__(DeliveryController.class).read();
		closedDeliveries = __inject__(DeliveryController.class).read();
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Tableau de bord des livraisons";
	}
	
}
