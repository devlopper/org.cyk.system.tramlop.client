package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;
import java.util.ArrayList;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter //@Accessors(chain=true)
public class Delivery extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Agreement agreement;
	private Truck truck;
	private Driver driver;
	private Integer weightInKiloGram;
	private Product product;
	private Place arrivalPlace;
	private ArrayList<Task> tasks;
	
	public Task getTaskAt(Integer index) {
		if(index == null || index >= CollectionHelper.getSize(tasks))
			return null;
		return tasks.get(index);
	}
	
	public static final String FIELD_TRUCK = "truck";
	public static final String FIELD_DRIVER = "driver";
	public static final String FIELD_TASKS = "tasks";
}
