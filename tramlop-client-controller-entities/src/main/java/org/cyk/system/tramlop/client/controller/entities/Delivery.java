package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;
import java.util.ArrayList;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.instance.InstanceGetter;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
//@Accessors(chain=false) //TODO JSF do not support chaining
@NoArgsConstructor
public class Delivery extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Agreement agreement;
	private Truck truck;
	private Driver driver;
	private Integer weightInKiloGram;
	private Product product;
	private Place arrivalPlace;
	private Boolean closed;
	private ArrayList<Task> tasks;
	private ArrayList<Truck> trucks;
	
	public Delivery(String code,String agreementCode,String truckCode,String driverCode,Boolean closed) {
		setCode(code);
		setAgreementFromCode(agreementCode);
		setTruckFromCode(truckCode);
		setDriverFromCode(driverCode);
		setClosed(closed);
	}
	
	public Delivery(String code,String agreementCode,String truckCode,String driverCode) {
		this(code, agreementCode, truckCode, driverCode, null);
	}
	
	public Delivery setAgreementFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.agreement = null;
		else
			this.agreement = InstanceGetter.getInstance().getByBusinessIdentifier(Agreement.class, code);
		return this;
	}
	
	public Delivery setTruckFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.truck = null;
		else
			this.truck = InstanceGetter.getInstance().getByBusinessIdentifier(Truck.class, code);
		return this;
	}
	
	public Delivery setDriverFromCode(String code) {
		if(StringHelper.isBlank(code))
			this.driver = null;
		else
			this.driver = InstanceGetter.getInstance().getByBusinessIdentifier(Driver.class, code);
		return this;
	}
	
	public Task getTaskAt(Integer index) {
		if(index == null || index >= CollectionHelper.getSize(tasks))
			return null;
		return tasks.get(index);
	}
	
	public Delivery withWeightInKiloGram(Integer value) {
		setWeightInKiloGram(value);
		return this;
	}
	
	public static final String FIELD_TRUCK = "truck";
	public static final String FIELD_TRUCKS = "trucks";
	public static final String FIELD_DRIVER = "driver";
	public static final String FIELD_TASKS = "tasks";
	public static final String FIELD_CLOSED = "closed";

}
