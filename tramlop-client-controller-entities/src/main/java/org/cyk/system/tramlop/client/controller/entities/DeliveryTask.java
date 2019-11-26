package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;

import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class DeliveryTask extends AbstractDataIdentifiableSystemStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Task task;
	private Delivery delivery;
	private Integer weightInKiloGram;
	private Product product;
	private Place unloadingPlace;
	private Driver driver;
	private Truck truck;
	
	public DeliveryTask setDeliveryFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.delivery = null;
		else
			this.delivery = InstanceGetter.getInstance().getByBusinessIdentifier(Delivery.class, code);
		return this;
	}
	
	public DeliveryTask setTaskFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.task = null;
		else
			this.task = InstanceGetter.getInstance().getByBusinessIdentifier(Task.class, code);
		return this;
	}
	
	public DeliveryTask setProductFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.product = null;
		else
			this.product = InstanceGetter.getInstance().getByBusinessIdentifier(Product.class, code);
		return this;
	}
	
	public DeliveryTask setUnloadingPlaceFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.unloadingPlace = null;
		else
			this.unloadingPlace = InstanceGetter.getInstance().getByBusinessIdentifier(Place.class, code);
		return this;
	}
	
	public DeliveryTask setDriverFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.driver = null;
		else
			this.driver = InstanceGetter.getInstance().getByBusinessIdentifier(Driver.class, code);
		return this;
	}
	
	public DeliveryTask setTruckFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.truck = null;
		else
			this.truck = InstanceGetter.getInstance().getByBusinessIdentifier(Truck.class, code);
		return this;
	}
	
}
