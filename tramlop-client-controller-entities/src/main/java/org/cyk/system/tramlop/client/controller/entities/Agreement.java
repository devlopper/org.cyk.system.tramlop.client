package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Agreement extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Customer customer;
	private Product product;
	private Integer productWeightInKiloGram;
	private Place departurePlace;
	private List<Truck> trucks;
	private List<Place> arrivalPlaces;
	
	public List<Truck> getTrucks(Boolean injectIfNull) {
		if(trucks == null && Boolean.TRUE.equals(injectIfNull))
			trucks = new ArrayList<Truck>();
		return trucks;
	}
	
	public List<Place> getArrivalPlaces(Boolean injectIfNull) {
		if(arrivalPlaces == null && Boolean.TRUE.equals(injectIfNull))
			arrivalPlaces = new ArrayList<Place>();
		return arrivalPlaces;
	}
}
