package org.cyk.system.tramlop.client.controller.impl.agreement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.api.DeliveryController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.system.tramlop.client.controller.entities.Task;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.constant.ConstantCharacter;
import org.cyk.utility.__kernel__.number.NumberHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.omnifaces.util.Faces;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class AgreementReadPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Agreement agreement;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		agreement = __inject__(AgreementController.class).readBySystemIdentifier(Faces.getRequestParameter("entityidentifier"),new Properties()
				.setFields(StringHelper.concatenate(CollectionHelper.listOf(Agreement.FIELD_PRODUCTS,Agreement.FIELD_ARRIVAL_PLACES,Agreement.FIELD_TRUCKS)
						, ConstantCharacter.COMA.toString())));
		
		Collection<Delivery> __deliveries__ = __inject__(DeliveryController.class).read(new Properties().setFields(Delivery.FIELD_TASKS+","+Delivery.FIELD_TASKS+"."+Task.FIELD_EXISTENCE
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_WEIGHT_IN_KILO_GRAM+","+Delivery.FIELD_TASKS+"."+Task.FIELD_PRODUCT
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_UNLOADING_PLACE+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_LOAD
				+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_UNLOAD+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_LOST));
		
		//TODO those data should be treated from persistence request
		if(CollectionHelper.isNotEmpty(__deliveries__)) {
			for(Delivery delivery : __deliveries__) {
				if(delivery.getAgreement().equals(agreement)) {
					if(delivery.getTaskAt(1) == null)
						continue;
					Product product = null;
					if(agreement.getProducts() != null)
						for(Product index : agreement.getProducts())
							if(delivery.getTaskAt(1) != null && index.equals(delivery.getTaskAt(1).getProduct())) {
								product = index;
								break;
							}
					
					if(product == null && delivery.getTaskAt(1) != null) {
						product = delivery.getTaskAt(1).getProduct();
						if(agreement.getProducts() == null)
							agreement.setProducts(new ArrayList<>());
						agreement.getProducts().add(product);
					}
					
					if(product == null)
						continue;
					
					product.setWeightInKiloGramOfProductAfterLoad(
							NumberHelper.getInteger(NumberHelper.add(product.getWeightInKiloGramOfProductAfterLoad(),delivery.getWeightInKiloGramOfProductAfterLoad())));
					product.setWeightInKiloGramOfProductAfterUnload(
							NumberHelper.getInteger(NumberHelper.add(product.getWeightInKiloGramOfProductAfterUnload(),delivery.getWeightInKiloGramOfProductAfterUnload())));
					product.setWeightInKiloGramOfProductLost(
							NumberHelper.getInteger(NumberHelper.add(product.getWeightInKiloGramOfProductLost(),delivery.getWeightInKiloGramOfProductLost())));	
				}		
			}	
		}		
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Contrat N° "+agreement.getCode();
	}
}
