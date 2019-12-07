package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.api.DeliveryController;
import org.cyk.system.tramlop.client.controller.api.ProductController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.system.tramlop.client.controller.entities.Task;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.omnifaces.util.Faces;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryListPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Collection<Delivery> deliveries;
	private Agreement agreement;
	private Product product;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		if(StringHelper.isNotBlank(Faces.getRequestParameter("agreement")))
			agreement = __inject__(AgreementController.class).readBySystemIdentifier(Faces.getRequestParameter("agreement"));
		if(StringHelper.isNotBlank(Faces.getRequestParameter("product")))
			product = __inject__(ProductController.class).readBySystemIdentifier(Faces.getRequestParameter("product"));
		
		Collection<Delivery> __deliveries__ = __inject__(DeliveryController.class).read(new Properties().setFields(Delivery.FIELD_TASKS+","+Delivery.FIELD_TASKS+"."+Task.FIELD_EXISTENCE
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_WEIGHT_IN_KILO_GRAM+","+Delivery.FIELD_TASKS+"."+Task.FIELD_PRODUCT
				+","+Delivery.FIELD_TASKS+"."+Task.FIELD_UNLOADING_PLACE+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_LOAD
				+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_AFTER_UNLOAD+","+Delivery.FIELD_WEIGHT_IN_KILO_GRAM_OF_PRODUCT_LOST));
		
		//TODO those data should be treated from persistence request
		if(CollectionHelper.isNotEmpty(__deliveries__)) {
			if(agreement != null && product != null) {
				for(Delivery delivery : __deliveries__) {
					if(delivery.getAgreement().equals(agreement) && delivery.getTaskAt(1) != null && delivery.getTaskAt(1).getProduct().equals(product)) {
						if(deliveries == null)
							deliveries = new ArrayList<>();
						deliveries.add(delivery);
					}		
				}		
			}
		}		
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		StringBuilder stringBuilder = new StringBuilder("Liste des livraisons");
		if(agreement != null)
			stringBuilder.append(" - Contrat N° "+agreement.getCode());
		if(product != null)
			stringBuilder.append(" - Produit : "+product.getName());
		return stringBuilder.toString();
	}
}
