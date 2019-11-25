package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.DeliveryTaskController;
import org.cyk.system.tramlop.client.controller.entities.DeliveryTask;
import org.cyk.system.tramlop.client.controller.entities.Driver;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.utility.__kernel__.instance.SelectionOne;
import org.cyk.utility.__kernel__.properties.Properties;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryLoadPage extends AbstractDeliveryPage implements Serializable {
	private static final long serialVersionUID = 1L;

	private DeliveryTask deliveryTask = new DeliveryTask();	
	private SelectionOne<Product> product;
	private SelectionOne<Driver> driver;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		product = new SelectionOne<Product>(Product.class);
		driver = new SelectionOne<Driver>(Driver.class);
	}
	
	@Override
	protected Properties __getReadTrucksProperties__() {
		return super.__getReadTrucksProperties__()
				.setQueryIdentifier(TruckPersistence.READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST);
	}
	
	public void __save__() {
		deliveryTask.setDelivery(null);
		//deliveryTask.set
		__inject__(DeliveryTaskController.class).create(deliveryTask);
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Chargement de camion";
	}
	
}
