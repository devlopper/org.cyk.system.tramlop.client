package org.cyk.system.tramlop.client.controller.impl.agreement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementArrivalPlaceController;
import org.cyk.system.tramlop.client.controller.api.AgreementProductController;
import org.cyk.system.tramlop.client.controller.api.AgreementTruckController;
import org.cyk.system.tramlop.client.controller.api.DeliveryController;
import org.cyk.system.tramlop.client.controller.entities.AgreementArrivalPlace;
import org.cyk.system.tramlop.client.controller.entities.AgreementProduct;
import org.cyk.system.tramlop.client.controller.entities.AgreementTruck;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.system.tramlop.client.controller.entities.Place;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.system.tramlop.client.controller.entities.Task;
import org.cyk.system.tramlop.client.controller.entities.Truck;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.number.NumberHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.system.action.SystemActionCustom;
import org.cyk.utility.client.controller.component.command.Commandable;
import org.cyk.utility.client.controller.component.command.CommandableBuilder;
import org.cyk.utility.client.controller.web.ComponentHelper;
import org.cyk.utility.client.controller.web.jsf.primefaces.model.AutoCompleteEntity;
import org.cyk.utility.client.controller.web.jsf.primefaces.model.AutoCompleteEntityBuilder;
import org.cyk.utility.client.controller.web.jsf.primefaces.model.input.DataTableEntity;
import org.cyk.utility.client.controller.web.jsf.primefaces.model.input.DataTableEntityBuilder;
import org.primefaces.PrimeFaces;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class AgreementReadPage extends AbstractAgreementReadPage implements Serializable {
	private static final long serialVersionUID = 1L;

	private AgreementArrivalPlace agreementArrivalPlace;
	private AgreementTruck agreementTruck;
	
	DataTableEntity<AgreementProduct> agreementProductDataTable;
	private String dialogAction,dialogTitle;
	protected Commandable dialogCommandable;
	
	private AutoCompleteEntity<Product> products;
	private AutoCompleteEntity<Place> arrivalPlaces;
	private AutoCompleteEntity<Truck> trucks;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		agreementProductDataTable = DataTableEntityBuilder.build(AgreementProduct.class);
		agreementProductDataTable.setTitle("Produits");
		agreementProductDataTable.setValue(agreement.getAgreementProducts());
		
		products = AutoCompleteEntityBuilder.build(Product.class);
		products.setMultiple(Boolean.TRUE);
		
		arrivalPlaces = AutoCompleteEntityBuilder.build(Place.class);
		arrivalPlaces.setMultiple(Boolean.TRUE);
		
		trucks = AutoCompleteEntityBuilder.build(Truck.class);
		trucks.setMultiple(Boolean.TRUE);
		
		Collection<Delivery> __deliveries__ = __inject__(DeliveryController.class).read(new Properties().setFields(Delivery.FIELD_TASKS+","+Delivery.FIELD_TASKS+"."+Task.FIELD_EXISTENCE
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_WEIGHT_IN_KILO_GRAM+","+Delivery.FIELD_TASKS+"."+Task.FIELD_PRODUCT
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_UNLOADING_PLACE+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_LOAD
				+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_UNLOAD+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_LOST));
		
		//TODO those data should be treated from persistence request
		if(CollectionHelper.isNotEmpty(__deliveries__)) {
			for(Delivery delivery : __deliveries__) {
				if(delivery.getAgreement().equals(agreement)) {
					if(delivery.getTaskAt(1) == null)
						continue;
					Product product = null;
					if(agreement.getProducts() != null)
						for(Product index : agreement.getProducts())
							if(delivery.getTaskAt(1) != null && index.equals(delivery.getTaskAt(1).getProduct())) {
								product = index;
								break;
							}
					
					if(product == null && delivery.getTaskAt(1) != null) {
						product = delivery.getTaskAt(1).getProduct();
						if(agreement.getProducts() == null)
							agreement.setProducts(new ArrayList<>());
						agreement.getProducts().add(product);
					}
					
					if(product == null)
						continue;
					
					product.setWeightInKiloGramOfProductAfterLoad(
							NumberHelper.getInteger(NumberHelper.add(product.getWeightInKiloGramOfProductAfterLoad(),delivery.getWeightInKiloGramOfProductAfterLoad())));
					product.setWeightInKiloGramOfProductAfterUnload(
							NumberHelper.getInteger(NumberHelper.add(product.getWeightInKiloGramOfProductAfterUnload(),delivery.getWeightInKiloGramOfProductAfterUnload())));
					product.setWeightInKiloGramOfProductLost(
							NumberHelper.getInteger(NumberHelper.add(product.getWeightInKiloGramOfProductLost(),delivery.getWeightInKiloGramOfProductLost())));	
				}		
			}	
		}
		
		CommandableBuilder dialogActionCommandableBuilder = __inject__(CommandableBuilder.class);
		dialogActionCommandableBuilder.setName("Oui").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					act();
				}
			}
		);
		dialogActionCommandableBuilder.addUpdatables(__inject__(ComponentHelper.class).getGlobalMessagesTargetsIdentifiers());
		dialogCommandable = dialogActionCommandableBuilder.execute().getOutput();
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Contrat N° "+agreement.getCode();
	}

	public void openDialog(String action) {
		dialogAction = action;
		if("add.product".equals(action)) {
			dialogTitle = "Ajout de produit au contrat N°"+agreement.getCode();
		}else if("add.arrival.place".equals(action)) {
			
		}else if("add.truck".equals(action)) {
			
		}
		PrimeFaces.current().executeScript("PF('dialog').show();");
	}
	
	@SuppressWarnings("unchecked")
	public void act() {
		if("add.product".equals(dialogAction) && CollectionHelper.isNotEmpty((Collection<?>) products.getValue())) {
			__inject__(AgreementProductController.class).createMany( ((Collection<Product>)products.getValue()).stream()
					.map(product -> new AgreementProduct().setAgreement(agreement).setProduct(product).setWeightInKiloGram(0)).collect(Collectors.toList()));
		}else if("add.arrival.place".equals(dialogAction) && CollectionHelper.isNotEmpty((Collection<?>) arrivalPlaces.getValue())) {
			__inject__(AgreementArrivalPlaceController.class).createMany( ((Collection<Place>)arrivalPlaces.getValue()).stream()
					.map(place -> new AgreementArrivalPlace().setAgreement(agreement).setPlace(place).setDurationInMinute(0)).collect(Collectors.toList()));
		}else if("add.truck".equals(dialogAction) && CollectionHelper.isNotEmpty((Collection<?>) trucks.getValue())) {
			__inject__(AgreementTruckController.class).createMany( ((Collection<Truck>)trucks.getValue()).stream()
					.map(truck -> new AgreementTruck().setAgreement(agreement).setTruck(truck).setDriver(null)).collect(Collectors.toList()));
		}
		dialogAction = null;		
		dialogTitle = null;
	}
	
	public void removeProduct(AgreementProduct agreementProduct) {
		if(agreementProduct == null)
			return;
		__inject__(AgreementProductController.class).delete(agreementProduct);
		agreement.getAgreementProducts().remove(agreementProduct);
	}
	
	public void removeTruck(AgreementTruck agreementTruck) {
		if(agreementTruck == null)
			return;
		__inject__(AgreementTruckController.class).delete(agreementTruck);
		agreement.getAgreementTrucks().remove(agreementTruck);
	}
	
	public void removeArrivalPlace(AgreementArrivalPlace agreementArrivalPlace) {
		if(agreementArrivalPlace == null)
			return;
		__inject__(AgreementArrivalPlaceController.class).delete(agreementArrivalPlace);
		agreement.getAgreementArrivalPlaces().remove(agreementArrivalPlace);
	}
}
