package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.system.tramlop.client.controller.entities.Truck;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.number.NumberHelper;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryListWhereClosedIsFalsePage extends AbstractDeliveryListPage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Collection<Truck> trucks;
	
	@Override
	protected void __process__(Collection<Delivery> __deliveries__) {
		//TODO those data should be treated from persistence request
		if(CollectionHelper.isNotEmpty(__deliveries__)) {
			for(Delivery delivery : __deliveries__) {
				if(Boolean.TRUE.equals(delivery.getClosed())) {
					Product product = delivery.getTaskAt(1).getProduct();
					Truck truck = null;
					if(trucks == null) {
						
					}else {
						for(Truck index : trucks)
							if(index.equals(delivery.getTruck()) && index.getProduct().equals(product)) {
								truck = index;
								break;
							}
					}
					if(truck == null) {
						truck = delivery.getTruck();
						if(trucks == null)
							trucks = new ArrayList<>();
						trucks.add(truck);
					}
					truck.setProduct(product);
					truck.setAgreement(delivery.getAgreement());
					truck.setWeightInKiloGramOfProductAfterLoad(
							NumberHelper.getInteger(NumberHelper.add(truck.getWeightInKiloGramOfProductAfterLoad(),delivery.getWeightInKiloGramOfProductAfterLoad())));
					truck.setWeightInKiloGramOfProductAfterUnload(
							NumberHelper.getInteger(NumberHelper.add(truck.getWeightInKiloGramOfProductAfterUnload(),delivery.getWeightInKiloGramOfProductAfterUnload())));
					truck.setWeightInKiloGramOfProductLost(
							NumberHelper.getInteger(NumberHelper.add(truck.getWeightInKiloGramOfProductLost(),delivery.getWeightInKiloGramOfProductLost())));										
				}else {
					if(deliveries == null)
						deliveries = new ArrayList<>();
					deliveries.add(delivery);
				}				
			}	
		}		
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Liste des livraisons en cours";
	}
	
}
