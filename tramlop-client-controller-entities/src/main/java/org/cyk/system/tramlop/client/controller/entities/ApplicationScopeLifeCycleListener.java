package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.utility.__kernel__.klass.PersistableClassesGetter;
import org.cyk.utility.client.controller.AbstractApplicationScopeLifeCycleListenerEntities;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListenerEntities implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(Object object) {
		super.__initialize__(object);
		PersistableClassesGetter.COLLECTION.set(List.of(
				IncidentResolution.class,Incident.class,Loading.class,Weighing.class,DeliveryTask.class,Delivery.class
				,AgreementArrivalPlace.class,AgreementPath.class,AgreementProduct.class,AgreementTruck.class,AgreementTruckSecondaryDriver.class,Agreement.class
				,Path.class,Customer.class,Driver.class,Place.class,Truck.class,Product.class,Task.class,IncidentType.class));
	}
	
	@Override
	public void __destroy__(Object object) {}
	
	/**/
	
}
