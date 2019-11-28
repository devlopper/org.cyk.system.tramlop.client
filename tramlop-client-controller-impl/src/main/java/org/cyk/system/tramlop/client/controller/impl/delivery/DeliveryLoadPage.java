package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.DeliveryTaskController;
import org.cyk.system.tramlop.client.controller.api.DriverController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
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

	private SelectionOne<Product> product;
	private SelectionOne<Driver> driver;
	private SelectionOne<Place> unloadingPlace;
	
	@Override
	protected String __getTaskCode__() {
		return Task.CODE_CHARGE;
	}
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		product = new SelectionOne<Product>(Product.class).setAreChoicesGettable(Boolean.FALSE);
		driver = new SelectionOne<Driver>(Driver.class).setAreChoicesGettable(Boolean.FALSE);
		unloadingPlace = new SelectionOne<Place>(Place.class).setAreChoicesGettable(Boolean.FALSE);
	}
	
	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
		if(agreement == null) {
			product.setChoices(null);
			unloadingPlace.setChoices(null);
			driver.setChoices(null);
		}else {
			product.setChoices(agreement.getProducts());
			unloadingPlace.setChoices(agreement.getArrivalPlaces());
			driver.setChoices(__inject__(DriverController.class).read(new Properties().setFilters(new FilterDto()
					.addField(Driver.FIELD_TRUCKS, CollectionHelper.listOf(truck.getValue().getCode()))
					.addField(Driver.FIELD_AGREEMENTS, CollectionHelper.listOf(agreement.getCode())))));
			driver.setValue(null);
		}
	}
	
	@Override
	protected Properties __getReadTrucksProperties__() {
		return super.__getReadTrucksProperties__()
				.setFilters(new FilterDto().addField(Truck.FIELD_TASKS, CollectionHelper.listOf(Task.CODE_PESE_VIDE_AVANT_CHARGE)))
				.setFields(Truck.FIELD_DRIVER+","+Truck.FIELD_DRIVERS);
	}
	
	@Override
	protected void __processOnSelect__(Truck truck) {
		super.__processOnSelect__(truck);		
		if(driver.getValue() == null && CollectionHelper.getSize(driver.getChoices()) == 1) {
			driver.setValue(CollectionHelper.getFirst(driver.getChoices()));
		}
		if(driver.getValue() == null) {
			if(truck.getDriver() != null && CollectionHelper.isNotEmpty(driver.getChoices())) {
				for(Driver index : driver.getChoices())
					if(index.getIdentifier().equals(truck.getDriver().getIdentifier())) {
						driver.select(index);
						break;
					}
			}	
		}				
	}
	
	public void __save__() {
		deliveryTask.setTruck(truck.getValue()).setDriver(driver.getValue()).setProduct(product.getValue()).setUnloadingPlace(unloadingPlace.getValue());
		__inject__(DeliveryTaskController.class).create(deliveryTask);
	}
	
}
