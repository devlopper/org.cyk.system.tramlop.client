package org.cyk.system.tramlop.client.controller.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.CustomerController;
import org.cyk.system.tramlop.client.controller.api.DriverController;
import org.cyk.system.tramlop.client.controller.api.PlaceController;
import org.cyk.system.tramlop.client.controller.api.ProductController;
import org.cyk.system.tramlop.client.controller.api.TruckController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Customer;
import org.cyk.system.tramlop.client.controller.entities.Driver;
import org.cyk.system.tramlop.client.controller.entities.Place;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.system.tramlop.client.controller.entities.Truck;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.system.action.SystemActionCreate;
import org.cyk.utility.client.controller.component.window.WindowContainerManagedWindowBuilder;
import org.cyk.utility.client.controller.component.window.WindowContainerManagedWindowBuilderGetter;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class AgreementCreatePage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Agreement agreement;
	private Collection<Customer> customers;
	private Collection<Product> products;
	private Collection<Truck> trucks;
	private Truck truck;
	private Collection<Driver> drivers;
	private Driver driver;
	private Collection<Place> departurePlaces;
	private Place departurePlace;
	private Collection<Place> arrivalPlaces;
	private Place arrivalPlace;
	private Integer productWeightInKiloGram;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		agreement = new Agreement();
		customers = __inject__(CustomerController.class).read(new Properties().setIsPageable(Boolean.FALSE));
		products = __inject__(ProductController.class).read(new Properties().setIsPageable(Boolean.FALSE));
		trucks = __inject__(TruckController.class).read(new Properties().setIsPageable(Boolean.FALSE));
		drivers = __inject__(DriverController.class).read(new Properties().setIsPageable(Boolean.FALSE));
		departurePlaces = __inject__(PlaceController.class).read(new Properties().setIsPageable(Boolean.FALSE));
		arrivalPlaces = __inject__(PlaceController.class).read(new Properties().setIsPageable(Boolean.FALSE));
	}
	
	public void addTruck() {
		if(truck == null || driver == null)
			return;
		truck.setDriver(driver);
		agreement.getTrucks(Boolean.TRUE).add(truck);
		trucks.remove(truck);
	}
	
	public void removeTruck(Truck truck) {
		if(truck == null || driver == null)
			return;
		agreement.getTrucks().remove(truck);
		truck.setDriver(null);
		trucks.add(truck);
	}
	
	public void addArrivalPlace() {
		if(arrivalPlace == null)
			return;
		agreement.getArrivalPlaces(Boolean.TRUE).add(arrivalPlace);
		arrivalPlaces.remove(arrivalPlace);
	}
	
	public void removeArrivalPlace(Place arrivalPlace) {
		if(arrivalPlace == null)
			return;
		agreement.getArrivalPlaces().remove(arrivalPlace);
		arrivalPlaces.add(arrivalPlace);
	}
	
	protected WindowContainerManagedWindowBuilder __getWindowContainerManagedWindowBuilder__() {
		return __inject__(WindowContainerManagedWindowBuilderGetter.class).setContainerManaged(this)
				.setSystemAction(__inject__(SystemActionCreate.class).setEntityClass(Agreement.class))
				.execute().getOutput();
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Création d'un contrat";
	}
	
}
