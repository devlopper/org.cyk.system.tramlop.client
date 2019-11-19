package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.DeliveryController;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.system.tramlop.client.controller.entities.SelectionOneDriver;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.utility.__kernel__.properties.Properties;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryWeighBeforeChargePage extends AbstractDeliveryPage implements Serializable {
	private static final long serialVersionUID = 1L;

	protected SelectionOneDriver driver;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		driver = new SelectionOneDriver();
	}
	
	@Override
	protected Properties __getReadTrucksProperties__() {
		return super.__getReadTrucksProperties__()
				.setQueryIdentifier(TruckPersistence.READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST);
	}
	
	public void __save__() {
		Delivery delivery = new Delivery();
		delivery.setTruck(truck.getValue()).setDriver(driver.getValue());
		__inject__(DeliveryController.class).create(delivery);
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Pesée de camion vide avant chargement";
	}
	
}
