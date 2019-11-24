package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor //@Accessors(chain=true)
public class Agreement extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Customer customer;
	private Place departurePlace;
	private List<Product> products;
	private List<Truck> trucks;
	private List<Place> arrivalPlaces;
	
	public Agreement(String code,String customerCode,String departurePlaceCode) {
		setCode(code);
		setCustomerFromCode(customerCode);
		setDeparturePlaceFromCode(departurePlaceCode);
	}
	
	public Agreement setCustomerFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.customer = null;
		else
			this.customer = InstanceGetter.getInstance().getByBusinessIdentifier(Customer.class, code);
		return this;
	}
	
	public Agreement setDeparturePlaceFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.departurePlace = null;
		else
			this.departurePlace = InstanceGetter.getInstance().getByBusinessIdentifier(Place.class, code);
		return this;
	}
	
	public List<Product> getProducts(Boolean injectIfNull) {
		if(products == null && Boolean.TRUE.equals(injectIfNull))
			products = new ArrayList<Product>();
		return products;
	}
	
	public Agreement addProductsFromCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return this;
		for(String code : codes)
			getProducts(Boolean.TRUE).add(InstanceGetter.getInstance().getByBusinessIdentifier(Product.class, code));
		return this;
	}
	
	public Agreement addProductsFromCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return this;
		addProductsFromCodes(CollectionHelper.listOf(codes));
		return this;
	}
	
	public Agreement addProduct(String code,Integer weightInKiloGram) {
		if(StringHelper.isBlank(code))
			return this;
		getProducts(Boolean.TRUE).add(InstanceGetter.getInstance().getByBusinessIdentifier(Product.class, code).setWeightInKiloGram(weightInKiloGram));
		return this;
	}
	
	public List<Truck> getTrucks(Boolean injectIfNull) {
		if(trucks == null && Boolean.TRUE.equals(injectIfNull))
			trucks = new ArrayList<Truck>();
		return trucks;
	}
	
	public Agreement addTrucksFromCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return this;
		for(String code : codes)
			getTrucks(Boolean.TRUE).add(InstanceGetter.getInstance().getByBusinessIdentifier(Truck.class, code));
		return this;
	}
	
	public Agreement addTrucksFromCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return this;
		addTrucksFromCodes(CollectionHelper.listOf(codes));
		return this;
	}
	
	public Agreement addTruck(String code,String driverCode) {
		if(StringHelper.isBlank(code))
			return this;
		getTrucks(Boolean.TRUE).add(InstanceGetter.getInstance().getByBusinessIdentifier(Truck.class, code).setDriverFromCode(driverCode));
		return this;
	}
	
	public List<Place> getArrivalPlaces(Boolean injectIfNull) {
		if(arrivalPlaces == null && Boolean.TRUE.equals(injectIfNull))
			arrivalPlaces = new ArrayList<Place>();
		return arrivalPlaces;
	}
	
	public Agreement addArrivalPlacesFromCodes(Collection<String> codes) {
		if(CollectionHelper.isEmpty(codes))
			return this;
		for(String code : codes)
			getArrivalPlaces(Boolean.TRUE).add(InstanceGetter.getInstance().getByBusinessIdentifier(Place.class, code));
		return this;
	}
	
	public Agreement addArrivalPlacesFromCodes(String...codes) {
		if(ArrayHelper.isEmpty(codes))
			return this;
		addArrivalPlacesFromCodes(CollectionHelper.listOf(codes));
		return this;
	}
	
	public Agreement addArrivalPlace(String code,Integer durationInMinute) {
		if(StringHelper.isBlank(code))
			return this;
		getArrivalPlaces(Boolean.TRUE).add(InstanceGetter.getInstance().getByBusinessIdentifier(Place.class, code).setDurationInMinute(durationInMinute));
		return this;
	}
}
