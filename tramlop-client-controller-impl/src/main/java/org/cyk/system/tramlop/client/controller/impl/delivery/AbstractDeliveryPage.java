package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.api.DeliveryController;
import org.cyk.system.tramlop.client.controller.api.TaskController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.system.tramlop.client.controller.entities.DeliveryTask;
import org.cyk.system.tramlop.client.controller.entities.Task;
import org.cyk.system.tramlop.client.controller.entities.Truck;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.system.action.SystemActionCustom;
import org.cyk.utility.client.controller.component.command.Commandable;
import org.cyk.utility.client.controller.component.command.CommandableBuilder;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.cyk.utility.client.controller.web.jsf.primefaces.model.SelectionOne;
import org.cyk.utility.server.persistence.query.filter.FilterDto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AbstractDeliveryPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Agreement agreement;
	protected Delivery delivery;
	protected DeliveryTask deliveryTask;
	protected Commandable saveCommandable;
	protected SelectionOne<Truck> truck;
	protected Task task;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		task = __inject__(TaskController.class).readByBusinessIdentifier(__getTaskCode__());
		if(task.getOrderNumber() == 1) {
			delivery = new Delivery();
		}else {
			deliveryTask = new DeliveryTask();
			deliveryTask.setTask(task);
		}
		try {
			truck = __getTrucks__();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		if(truck != null) {
			truck.setListener(new SelectionOne.Listener<Truck>() {
				@Override
				public void processOnSelect(Truck truck) {
					__processOnSelect__(truck);
				}
			});
		}		
		CommandableBuilder saveCommandableBuilder = __inject__(CommandableBuilder.class);
		saveCommandableBuilder.setName("Enregistrer").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					__save__();
				}
			}
		);
		saveCommandable = saveCommandableBuilder.execute().getOutput();
	}
	
	protected SelectionOne<Truck> __getTrucks__() {
		return new SelectionOne<Truck>(Truck.class,__getReadTrucksProperties__());
	}
	
	protected Properties __getReadTrucksProperties__() {
		return new Properties()
			.setIsPageable(Boolean.FALSE)
			.setQueryIdentifier(TruckPersistence.READ_WHERE_DELIVERY_CLOSED_IS_FALSE_BY_TASKS_COUNTS)
			.setFilters(new FilterDto().addField(Truck.FIELD_TASKS_COUNTS, CollectionHelper.listOf(Long.valueOf(task.getOrderNumber()-1))))
			.setFields(Truck.FIELD_DRIVER+","+Truck.FIELD_DRIVERS);
	}
	
	protected abstract String __getTaskCode__();
	
	@Override
	protected String __getWindowTitleValue__() {
		return task.getName();
	}
	
	protected void __processOnSelect__(Truck truck) {
		setAgreement(CollectionHelper.getFirst(__inject__(AgreementController.class).read(new Properties()
				.setFilters(new FilterDto().addField(Agreement.FIELD_TRUCKS, CollectionHelper.listOf(truck.getCode())))
				.setFields(Agreement.FIELD_PRODUCTS+","+Agreement.FIELD_ARRIVAL_PLACES+","+Agreement.FIELD_DRIVERS)
				)));
		
		if(task.getOrderNumber() == 1) {
			
		}else {
			delivery = CollectionHelper.getFirst(__inject__(DeliveryController.class).read(new Properties()
					.setFields(Delivery.FIELD_DRIVER+","+Delivery.FIELD_TASKS+","+Delivery.FIELD_TASKS+"."+Task.FIELD_EXISTENCE
							+","+Delivery.FIELD_TASKS+"."+Task.FIELD_WEIGHT_IN_KILO_GRAM+","+Delivery.FIELD_TASKS+"."+Task.FIELD_PRODUCT
							+","+Delivery.FIELD_TASKS+"."+Task.FIELD_UNLOADING_PLACE)
					.setFilters(new FilterDto()
					.addField(Delivery.FIELD_CLOSED, Boolean.FALSE)
					.addField(Delivery.FIELD_TRUCKS, CollectionHelper.listOf(truck.getCode())))));
			deliveryTask.setDelivery(delivery);
		}
	}
	
	protected void __save__() {
		
	}
	
}
