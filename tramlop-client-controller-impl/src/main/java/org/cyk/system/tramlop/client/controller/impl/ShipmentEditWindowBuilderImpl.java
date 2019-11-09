package org.cyk.system.tramlop.client.controller.impl;
import java.io.Serializable;

import org.cyk.system.tramlop.client.controller.entities.Shipment;
import org.cyk.system.tramlop.client.controller.entities.ShipmentEditWindowBuilder;
import org.cyk.utility.__kernel__.system.action.SystemAction;
import org.cyk.utility.__kernel__.system.action.SystemActionCreate;
import org.cyk.utility.client.controller.component.view.ViewBuilder;
import org.cyk.utility.client.controller.component.window.AbstractWindowContainerManagedWindowBuilderEditDataImpl;
import org.cyk.utility.client.controller.data.Data;
import org.cyk.utility.client.controller.data.Form;

public class ShipmentEditWindowBuilderImpl extends AbstractWindowContainerManagedWindowBuilderEditDataImpl implements ShipmentEditWindowBuilder, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __execute__(Form form,SystemAction systemAction,Data data,ViewBuilder viewBuilder) {
		/*viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Shipment.FIELD_LOADING,Loading.FIELD_PRODUCT);
		viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Shipment.FIELD_LOADING,Loading.FIELD_PRODUCT_WEIGHT);
		viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Shipment.FIELD_LOADING,Loading.FIELD_TRUCK);
		viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Shipment.FIELD_LOADING,Loading.FIELD_DRIVER);
		viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Shipment.FIELD_LOADING,Loading.FIELD_WEIGHT);
		viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Shipment.FIELD_LOADING,Loading.FIELD_CUSTOMER);
		*/
		viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Shipment.FIELD_PLACE_OF_DEPARTURE);
		viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Shipment.FIELD_DATE_OF_DEPARTURE);
		viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Shipment.FIELD_PLACE_OF_ARRIVAL);
		viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Shipment.FIELD_DATE_OF_ARRIVAL);
		//viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Shipment.FIELD_DURATION);
		
	}

	@Override
	protected Object __getEntity__(SystemAction systemAction) {
		Shipment userAccount = (Shipment) super.__getEntity__(systemAction);
		if(systemAction instanceof SystemActionCreate) {
			;//userAccount.setLoading(new Loading());
		}
		return userAccount;
	}

}
