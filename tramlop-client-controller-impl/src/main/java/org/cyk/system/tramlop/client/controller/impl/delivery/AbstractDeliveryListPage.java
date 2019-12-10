package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;
import java.util.Collection;

import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.DeliveryController;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.system.tramlop.client.controller.entities.Task;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import lombok.Getter;
import lombok.Setter;

@Named @Getter @Setter
public abstract class AbstractDeliveryListPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Collection<Delivery> deliveries;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		Collection<Delivery> __deliveries__ = __inject__(DeliveryController.class).read(new Properties().setFields(Delivery.FIELD_TASKS+","+Delivery.FIELD_TASKS+"."+Task.FIELD_EXISTENCE
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_WEIGHT_IN_KILO_GRAM+","+Delivery.FIELD_TASKS+"."+Task.FIELD_PRODUCT
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_UNLOADING_PLACE+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_LOAD
				+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_UNLOAD+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_LOST
				+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_LOSTABLE+","+Delivery.FIELD_PATH+","+Delivery.FIELD_DURATION_IN_MINUTE));
		__process__(__deliveries__);
	}
	
	protected abstract void __process__(Collection<Delivery> deliveries);
	
}
