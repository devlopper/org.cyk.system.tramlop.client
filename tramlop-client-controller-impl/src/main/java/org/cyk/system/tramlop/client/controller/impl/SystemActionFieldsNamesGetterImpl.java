package org.cyk.system.tramlop.client.controller.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.cyk.system.tramlop.client.controller.entities.shipment.Customer;
import org.cyk.system.tramlop.client.controller.entities.shipment.Driver;
import org.cyk.system.tramlop.client.controller.entities.shipment.Loading;
import org.cyk.system.tramlop.client.controller.entities.shipment.Shipment;
import org.cyk.system.tramlop.client.controller.entities.shipment.Unloading;
import org.cyk.utility.__kernel__.field.FieldHelper;
import org.cyk.utility.__kernel__.klass.ClassHelper;
import org.cyk.utility.__kernel__.system.action.SystemAction;
import org.cyk.utility.__kernel__.system.action.SystemActionList;
import org.cyk.utility.client.controller.AbstractSystemActionFieldsNamesGetterImpl;

@org.cyk.system.tramlop.server.annotation.System
public class SystemActionFieldsNamesGetterImpl extends AbstractSystemActionFieldsNamesGetterImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Collection<String> __get__(Class<? extends SystemAction> systemActionClass, Object systemActionIdentifier,Class<?> entityClass) {
		if(Shipment.class.equals(entityClass) && ClassHelper.isInstanceOf(systemActionClass, SystemActionList.class))
			return List.of(Shipment.FIELD_CODE,FieldHelper.join(Shipment.FIELD_LOADING,Loading.FIELD_PRODUCT),Shipment.FIELD_PLACE_OF_DEPARTURE,
					Shipment.FIELD_PLACE_OF_ARRIVAL,Shipment.FIELD_STATUS);
	
		if(Unloading.class.equals(entityClass) && ClassHelper.isInstanceOf(systemActionClass, SystemActionList.class))
			return List.of(Unloading.FIELD_CODE,Unloading.FIELD_SHIPMENT,Unloading.FIELD_WEIGHT,Unloading.FIELD_DATE_OF_ARRIVAL);
		
		if(Driver.class.equals(entityClass) && ClassHelper.isInstanceOf(systemActionClass, SystemActionList.class))
			return List.of(Driver.FIELD_CODE,Driver.FIELD_FIRST_NAME,Driver.FIELD_LAST_NAMES,Driver.FIELD_PHONE_NUMBER);
		
		if(Customer.class.equals(entityClass) && ClassHelper.isInstanceOf(systemActionClass, SystemActionList.class))
			return List.of(Customer.FIELD_CODE,Customer.FIELD_FIRST_NAME,Customer.FIELD_LAST_NAMES,Customer.FIELD_PHONE_NUMBER);
		
		return super.__get__(systemActionClass, systemActionIdentifier, entityClass);
	}
	
}