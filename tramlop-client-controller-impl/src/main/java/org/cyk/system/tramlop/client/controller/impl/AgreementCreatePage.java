package org.cyk.system.tramlop.client.controller.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.api.CustomerController;
import org.cyk.system.tramlop.client.controller.api.DriverController;
import org.cyk.system.tramlop.client.controller.api.PlaceController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Customer;
import org.cyk.system.tramlop.client.controller.entities.Driver;
import org.cyk.system.tramlop.client.controller.entities.Place;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.system.tramlop.client.controller.entities.Truck;
import org.cyk.utility.__kernel__.instance.SelectionMany;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.system.action.SystemActionCreate;
import org.cyk.utility.__kernel__.system.action.SystemActionCustom;
import org.cyk.utility.client.controller.component.command.Commandable;
import org.cyk.utility.client.controller.component.command.CommandableBuilder;
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
	//private Collection<Truck> trucks;
	//private Truck truck;
	private Collection<Driver> drivers;
	//private Driver driver;
	private Collection<Place> departurePlaces;
	private Place departurePlace;
	private Integer productWeightInKiloGram;
	
	private SelectionMany<Place> arrivalPlaces;
	private SelectionMany<Product> products;
	private SelectionMany<Truck> trucks;
	//private SelectionMany<Driver> drivers;
	
	private Commandable addTruckCommandable;
	private Commandable addArrivalPlaceCommandable;
	private Commandable addProductCommandable;
	private Commandable createCommandable;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		agreement = new Agreement();
		try {
			customers = __inject__(CustomerController.class).read(new Properties().setIsPageable(Boolean.FALSE));
			//trucks = __inject__(TruckController.class).read(new Properties().setIsPageable(Boolean.FALSE));
			drivers = __inject__(DriverController.class).read(new Properties().setIsPageable(Boolean.FALSE));
			departurePlaces = __inject__(PlaceController.class).read(new Properties().setIsPageable(Boolean.FALSE));
			products = new SelectionMany<Product>(Product.class);
			arrivalPlaces = new SelectionMany<Place>(Place.class);
			trucks = new SelectionMany<Truck>(Truck.class);
			//drivers = new SelectionMany<Driver>(Driver.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CommandableBuilder addTruckCommandableBuilder = __inject__(CommandableBuilder.class);
		addTruckCommandableBuilder.setName("Ajouter").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					trucks.select();
				}
			}
		);
		addTruckCommandableBuilder.getCommand().getFunction().setIsNotifyOnThrowableIsNull(Boolean.FALSE);
		//addTruckCommandableBuilder.addUpdatables("form:truck,form:driver,trucks");
		addTruckCommandableBuilder.addUpdatables("form:truck,trucks");
		addTruckCommandable = addTruckCommandableBuilder.execute().getOutput();
		
		CommandableBuilder addProductCommandableBuilder = __inject__(CommandableBuilder.class);
		addProductCommandableBuilder.setName("Ajouter").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					products.select();
				}
			}
		);
		addProductCommandableBuilder.getCommand().getFunction().setIsNotifyOnThrowableIsNull(Boolean.FALSE);
		addProductCommandableBuilder.addUpdatables("form:product,products");
		addProductCommandable = addProductCommandableBuilder.execute().getOutput();
		
		CommandableBuilder addArrivalPlaceCommandableBuilder = __inject__(CommandableBuilder.class);
		addArrivalPlaceCommandableBuilder.setName("Ajouter").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					arrivalPlaces.select();
				}
			}
		);
		addArrivalPlaceCommandableBuilder.getCommand().getFunction().setIsNotifyOnThrowableIsNull(Boolean.FALSE);
		addArrivalPlaceCommandableBuilder.addUpdatables("form:arrivalPlace,arrivalPlaces");
		addArrivalPlaceCommandable = addArrivalPlaceCommandableBuilder.execute().getOutput();
		
		CommandableBuilder saveCommandableBuilder = __inject__(CommandableBuilder.class);
		saveCommandableBuilder.setName("Créer").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					create();
				}
			}
		);
		createCommandable = saveCommandableBuilder.execute().getOutput();
	}
	/*
	public void addTruck() {
		if(truck == null || driver == null)
			return;
		truck.setDriver(driver);
		agreement.getTrucks(Boolean.TRUE).add(truck);
		trucks.remove(truck);
		drivers.remove(driver);
	}
	
	public void removeTruck(Truck truck) {
		if(truck == null)
			return;
		agreement.getTrucks().remove(truck);
		trucks.add(truck);
		drivers.add(truck.getDriver());
		truck.setDriver(null);
	}
	*/
	public void create() {
		agreement.setProducts((List<Product>) products.getValue());
		agreement.setTrucks((List<Truck>) trucks.getValue());
		agreement.setArrivalPlaces((List<Place>) arrivalPlaces.getValue());
		__inject__(AgreementController.class).create(agreement);
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
