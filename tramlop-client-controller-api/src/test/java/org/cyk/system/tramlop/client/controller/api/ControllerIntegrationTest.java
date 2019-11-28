package org.cyk.system.tramlop.client.controller.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cyk.system.tramlop.server.persistence.entities.Task.CODE_LOAD;
import static org.cyk.system.tramlop.server.persistence.entities.Task.CODE_WEIGH_AFTER_LOAD;
import static org.cyk.system.tramlop.server.persistence.entities.Task.CODE_WEIGH_AFTER_UNLOAD;
import static org.cyk.system.tramlop.server.persistence.entities.Task.CODE_WEIGH_BEFORE_LOAD;
import static org.cyk.system.tramlop.server.persistence.entities.Task.CODE_WEIGH_BEFORE_UNLOAD;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.system.tramlop.client.controller.entities.DeliveryTask;
import org.cyk.system.tramlop.client.controller.entities.Driver;
import org.cyk.system.tramlop.client.controller.entities.Task;
import org.cyk.system.tramlop.client.controller.entities.Truck;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.identifier.resource.ProxyGetter;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.client.controller.test.arquillian.AbstractControllerArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.server.persistence.query.filter.FilterDto;
import org.cyk.utility.server.representation.Representation;
import org.junit.Test;

public class ControllerIntegrationTest extends AbstractControllerArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBefore__() {
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);	
		super.__listenBefore__();
		ProxyGetter.getInstance().get(Representation.class).loadData();
	}
	
	@Test
	public void truck_READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST() throws Exception{
		Collection<Truck> trucks = __inject__(TruckController.class).read(new Properties()
				.setQueryIdentifier(TruckPersistence.READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST)
				.setIsQueryResultPaginated(Boolean.FALSE));
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}
	
	@Test
	public void agreement_create_withProducts_withArrivalPlaces_withTrucks() throws Exception{
		__inject__(AgreementController.class).create(new Agreement("a100","c01","p01").addProduct("p01", 1000).addArrivalPlace("p01", 30).addTruck("t1", "d1"));
		Collection<Truck> trucks = __inject__(TruckController.class).read(new Properties()
				.setQueryIdentifier(TruckPersistence.READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST)
				.setIsQueryResultPaginated(Boolean.FALSE));
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}
	
	@Test
	public void delivery_weightBeforeLoad(){
		__inject__(AgreementController.class).create(new Agreement("a100","c01","p01").addProduct("p01", 1000).addArrivalPlace("p01", 30).addTruck("t11", "d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryController.class).create(new Delivery(deliveryCode, null,"t11", null).withWeightInKiloGram(15248));
		Collection<Task> tasks = __inject__(TaskController.class).read(new Properties()
				.setFilters(new FilterDto().addField(Task.FIELD_DELIVERIES, CollectionHelper.listOf(deliveryCode)))
				.setIsQueryResultPaginated(Boolean.FALSE));
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(CODE_WEIGH_BEFORE_LOAD);
	}
	
	@Test
	public void delivery_load(){
		__inject__(AgreementController.class).create(new Agreement("a100","c01","p01").addProduct("p01", 1000).addArrivalPlace("p01", 30).addTruck("t11", "d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryController.class).create(new Delivery(deliveryCode, null,"t11", null).withWeightInKiloGram(15248));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_LOAD).setProductFromCode("p01").setUnloadingPlaceFromCode("p01").setDriverFromCode("d1"));		
		Collection<Task> tasks = __inject__(TaskController.class).read(new Properties()
				.setFilters(new FilterDto().addField(Task.FIELD_DELIVERIES, CollectionHelper.listOf(deliveryCode)))
				.setIsQueryResultPaginated(Boolean.FALSE));
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(CODE_WEIGH_BEFORE_LOAD,CODE_LOAD);
	}
	
	@Test
	public void delivery_weightAfterLoad(){
		__inject__(AgreementController.class).create(new Agreement("a100","c01","p01").addProduct("p01", 1000).addArrivalPlace("p01", 30).addTruck("t11", "d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryController.class).create(new Delivery(deliveryCode, null,"t11", null).withWeightInKiloGram(15248));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_LOAD).setProductFromCode("p01").setUnloadingPlaceFromCode("p01").setDriverFromCode("d1"));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_AFTER_LOAD).setWeightInKiloGram(150));
		Collection<Task> tasks = __inject__(TaskController.class).read(new Properties()
				.setFilters(new FilterDto().addField(Task.FIELD_DELIVERIES, CollectionHelper.listOf(deliveryCode)))
				.setIsQueryResultPaginated(Boolean.FALSE));
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(CODE_WEIGH_BEFORE_LOAD,CODE_LOAD,CODE_WEIGH_AFTER_LOAD);
	}
	
	@Test
	public void delivery_unload(){
		__inject__(AgreementController.class).create(new Agreement("a100","c01","p01").addProduct("p01", 1000).addArrivalPlace("p01", 30).addTruck("t11", "d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryController.class).create(new Delivery(deliveryCode, null,"t11", null).withWeightInKiloGram(15248));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_LOAD).setProductFromCode("p01").setUnloadingPlaceFromCode("p01").setDriverFromCode("d1"));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_AFTER_LOAD).setWeightInKiloGram(150));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_BEFORE_UNLOAD).setWeightInKiloGram(140));
		Collection<Task> tasks = __inject__(TaskController.class).read(new Properties()
				.setFilters(new FilterDto().addField(Task.FIELD_DELIVERIES, CollectionHelper.listOf(deliveryCode)))
				.setIsQueryResultPaginated(Boolean.FALSE));
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(CODE_WEIGH_BEFORE_LOAD,CODE_LOAD
				,CODE_WEIGH_AFTER_LOAD,CODE_WEIGH_BEFORE_UNLOAD);
	}
	
	@Test
	public void delivery_weightAfterUnLoad(){
		__inject__(AgreementController.class).create(new Agreement("a100","c01","p01").addProduct("p01", 1000).addArrivalPlace("p01", 30).addTruck("t11", "d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryController.class).create(new Delivery(deliveryCode, null,"t11", null).withWeightInKiloGram(15248));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_LOAD).setProductFromCode("p01").setUnloadingPlaceFromCode("p01").setDriverFromCode("d1"));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_AFTER_LOAD).setWeightInKiloGram(150));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_BEFORE_UNLOAD).setWeightInKiloGram(140));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_AFTER_UNLOAD).setWeightInKiloGram(500));
		Collection<Task> tasks = __inject__(TaskController.class).read(new Properties()
				.setFilters(new FilterDto().addField(Task.FIELD_DELIVERIES, CollectionHelper.listOf(deliveryCode)))
				.setIsQueryResultPaginated(Boolean.FALSE));
		assertThat(tasks).isNotEmpty();
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(CODE_WEIGH_BEFORE_LOAD,CODE_LOAD
				,CODE_WEIGH_AFTER_LOAD,CODE_WEIGH_BEFORE_UNLOAD,CODE_WEIGH_AFTER_UNLOAD);
	}
	
	@Test
	public void delivery_readTransientFields_tasks() {
		__inject__(AgreementController.class).create(new Agreement("a100","c01","p01").addProduct("p01", 1000).addArrivalPlace("p01", 30).addTruck("t11", "d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryController.class).create(new Delivery(deliveryCode, null,"t11", null).withWeightInKiloGram(15248));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_LOAD).setProductFromCode("p01").setUnloadingPlaceFromCode("p01").setDriverFromCode("d1"));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_AFTER_LOAD).setWeightInKiloGram(150));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_BEFORE_UNLOAD).setWeightInKiloGram(140));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_AFTER_UNLOAD).setWeightInKiloGram(500));
		
		Delivery delivery = __inject__(DeliveryController.class).readByBusinessIdentifier(deliveryCode);
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNull();
		delivery = __inject__(DeliveryController.class).readByBusinessIdentifier(deliveryCode,new Properties().setFields(Delivery.FIELD_TASKS));
		assertThat(delivery).isNotNull();
		assertThat(delivery.getTasks()).isNotEmpty();
		assertThat(delivery.getTasks().stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(CODE_WEIGH_BEFORE_LOAD,CODE_LOAD
				, CODE_WEIGH_AFTER_LOAD, CODE_WEIGH_BEFORE_UNLOAD, CODE_WEIGH_AFTER_UNLOAD);
	}
	
	@Test
	public void delivery_readTasks() {
		__inject__(AgreementController.class).create(new Agreement("a100","c01","p01").addProduct("p01", 1000).addArrivalPlace("p01", 30).addTruck("t11", "d1"));
		String deliveryCode = __getRandomCode__();
		__inject__(DeliveryController.class).create(new Delivery(deliveryCode, null,"t11", null).withWeightInKiloGram(15248));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_LOAD).setProductFromCode("p01").setUnloadingPlaceFromCode("p01").setDriverFromCode("d1"));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_AFTER_LOAD).setWeightInKiloGram(150));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_BEFORE_UNLOAD).setWeightInKiloGram(140));
		__inject__(DeliveryTaskController.class).create(new DeliveryTask().setTruckFromCode("t11").setTaskFromCode(CODE_WEIGH_AFTER_UNLOAD).setWeightInKiloGram(500));
		
		Collection<Delivery> deliveries = __inject__(DeliveryController.class).read(new Properties().setFields(Delivery.FIELD_TASKS+","+Delivery.FIELD_TASKS+"."+Task.FIELD_EXISTENCE
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_WEIGHT_IN_KILO_GRAM+","+Delivery.FIELD_TASKS+"."+Task.FIELD_PRODUCT));
		assertThat(deliveries).isNotNull();
	}
	
	@Test
	public void readTruckByDrivers_a1_t1() {
		Collection<Driver> drivers = __inject__(DriverController.class).read(new Properties().setFilters(new FilterDto()
				.addField(Driver.FIELD_TRUCKS, CollectionHelper.listOf("t1"))
				.addField(Driver.FIELD_AGREEMENTS, CollectionHelper.listOf("a1"))));
		assertThat(drivers).isNotNull();
		assertThat(drivers.stream().map(Driver::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("d1");
	}
	
	@Test
	public void readTruckByDrivers_a1_t2() {
		Collection<Driver> drivers = __inject__(DriverController.class).read(new Properties().setFilters(new FilterDto()
				.addField(Driver.FIELD_TRUCKS, CollectionHelper.listOf("t2"))
				.addField(Driver.FIELD_AGREEMENTS, CollectionHelper.listOf("a1"))));
		assertThat(drivers).isNotNull();
		assertThat(drivers.stream().map(Driver::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("d2","dA");
	}
}
