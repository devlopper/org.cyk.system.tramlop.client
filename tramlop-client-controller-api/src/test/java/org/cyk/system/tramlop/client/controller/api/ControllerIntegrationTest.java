package org.cyk.system.tramlop.client.controller.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
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
		assertThat(tasks.stream().map(Task::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder(org.cyk.system.tramlop.server.persistence.entities.Task.CODE_PESE_VIDE_AVANT_CHARGE);
	}
}
