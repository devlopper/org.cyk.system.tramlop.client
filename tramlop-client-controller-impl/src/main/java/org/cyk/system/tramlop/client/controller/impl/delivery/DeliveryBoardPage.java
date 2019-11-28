package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.DeliveryController;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.system.tramlop.client.controller.entities.Task;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.cyk.utility.client.controller.web.jsf.primefaces.dialog.DialogOpener;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryBoardPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Collection<Delivery> runningDeliveries,closedDeliveries;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		runningDeliveries = __inject__(DeliveryController.class).read(new Properties().setFields(Delivery.FIELD_TASKS+","+Delivery.FIELD_TASKS+"."+Task.FIELD_EXISTENCE
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_WEIGHT_IN_KILO_GRAM+","+Delivery.FIELD_TASKS+"."+Task.FIELD_PRODUCT
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_UNLOADING_PLACE));
		closedDeliveries = __inject__(DeliveryController.class).read();
	}
	
	public void showDialog(Delivery delivery) {
		DialogOpener.getInstance().openByEntity(delivery);
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Tableau de bord des livraisons";
	}
	
}
