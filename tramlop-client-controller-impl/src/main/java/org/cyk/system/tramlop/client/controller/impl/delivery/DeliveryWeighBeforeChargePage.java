package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.server.persistence.api.TruckPersistence;
import org.cyk.utility.__kernel__.properties.Properties;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryWeighBeforeChargePage extends AbstractDeliveryPage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Properties __getReadTrucksProperties__() {
		return super.__getReadTrucksProperties__()
				.setQueryIdentifier(TruckPersistence.READ_WHERE_AGREEMENT_CLOSED_IS_FALSE_EXIST_AND_DELIVERY_CLOSED_IS_FALSE_DOES_NOT_EXIST);
	}
	
	public void create() {
		
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Pesée de camion vide avant chargement";
	}
	
}
