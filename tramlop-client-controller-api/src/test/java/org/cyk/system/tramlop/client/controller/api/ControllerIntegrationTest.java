package org.cyk.system.tramlop.client.controller.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.cyk.system.tramlop.client.controller.entities.Truck;
import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.utility.__kernel__.identifier.resource.ProxyGetter;
import org.cyk.utility.__kernel__.identifier.resource.ProxyHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.client.controller.test.arquillian.AbstractControllerArquillianIntegrationTestWithDefaultDeployment;
import org.cyk.utility.server.representation.Representation;
import org.junit.Test;

public class ControllerIntegrationTest extends AbstractControllerArquillianIntegrationTestWithDefaultDeployment {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenBefore__() {
		__inject__(ApplicationScopeLifeCycleListener.class).initialize(null);	
		super.__listenBefore__();
		
	}
	
	@Test
	public void create_functionType() throws Exception{
		Collection<Truck> trucks = __inject__(TruckController.class).read(new Properties()
				.setQueryIdentifier(TruckPersistence.READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST)
				.setIsQueryResultPaginated(Boolean.FALSE));
		assertThat(trucks).isNotEmpty();
		assertThat(trucks.stream().map(Truck::getCode).collect(Collectors.toList())).containsExactlyInAnyOrder("t5","t10");
	}
	
}
