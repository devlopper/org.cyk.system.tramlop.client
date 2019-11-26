package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.DeliveryTaskController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.DeliveryTask;
import org.cyk.system.tramlop.client.controller.entities.Driver;
import org.cyk.system.tramlop.client.controller.entities.Place;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.system.tramlop.client.controller.entities.Truck;
import org.cyk.system.tramlop.server.persistence.entities.Task;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.instance.SelectionOne;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.query.filter.FilterDto;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryLoadPage extends AbstractDeliveryPage implements Serializable {
	private static final long serialVersionUID = 1L;

	private DeliveryTask deliveryTask = new DeliveryTask();	
	private Agreement agreement;
	private SelectionOne<Product> product;
	private SelectionOne<Driver> driver;
	private SelectionOne<Place> unloadingPlace;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		deliveryTask.setTaskFromCode(Task.CODE_CHARGE);
		product = new SelectionOne<Product>(Product.class);
		driver = new SelectionOne<Driver>(Driver.class);
		unloadingPlace = new SelectionOne<Place>(Place.class);
	}
	
	@Override
	protected Properties __getReadTrucksProperties__() {
		return super.__getReadTrucksProperties__()
				.setFilters(new FilterDto().addField(Truck.FIELD_TASKS, CollectionHelper.listOf(Task.CODE_PESE_VIDE_AVANT_CHARGE)));
	}
	
	public void __save__() {
		deliveryTask.setTruck(truck.getValue()).setDriver(driver.getValue()).setProduct(product.getValue()).setUnloadingPlace(unloadingPlace.getValue());
		__inject__(DeliveryTaskController.class).create(deliveryTask);
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Chargement de camion";
	}
	
}
