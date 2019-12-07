package org.cyk.system.tramlop.client.controller.impl.agreement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class AgreementListWhereClosedIsFalsePage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Collection<Agreement> agreements;
	private Collection<Product> products;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		Collection<Agreement> __agreements__ = __inject__(AgreementController.class).read(new Properties().setFields(Agreement.FIELD_PRODUCTS));
		
		//TODO those data should be treated from persistence request
		if(CollectionHelper.isNotEmpty(__agreements__)) {
			for(Agreement agreement : __agreements__) {
				if(Boolean.TRUE.equals(agreement.getClosed())) {
					/*
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
					truck.setWeightInKiloGramOfProductAfterLoad(
							NumberHelper.getInteger(NumberHelper.add(truck.getWeightInKiloGramOfProductAfterLoad(),delivery.getWeightInKiloGramOfProductAfterLoad())));
					truck.setWeightInKiloGramOfProductAfterUnload(
							NumberHelper.getInteger(NumberHelper.add(truck.getWeightInKiloGramOfProductAfterUnload(),delivery.getWeightInKiloGramOfProductAfterUnload())));
					truck.setWeightInKiloGramOfProductLost(
							NumberHelper.getInteger(NumberHelper.add(truck.getWeightInKiloGramOfProductLost(),delivery.getWeightInKiloGramOfProductLost())));
					*/
				}else {
					if(agreements == null)
						agreements = new ArrayList<>();
					agreements.add(agreement);
				}				
			}	
		}	
		
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Liste des contrats en cours";
	}
	
}
