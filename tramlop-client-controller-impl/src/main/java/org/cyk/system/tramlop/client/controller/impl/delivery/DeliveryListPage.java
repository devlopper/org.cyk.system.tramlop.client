package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.api.ProductController;
import org.cyk.system.tramlop.client.controller.api.TruckController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Delivery;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.system.tramlop.client.controller.entities.Truck;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.omnifaces.util.Faces;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryListPage extends AbstractDeliveryListPage implements Serializable {
	private static final long serialVersionUID = 1L;

	private Collection<Delivery> deliveries;
	private Agreement agreement;
	private Product product;
	private Truck truck;
	
	@Override
	protected void __listenBeforePostConstruct__() {
		super.__listenBeforePostConstruct__();
		if(StringHelper.isNotBlank(Faces.getRequestParameter("agreement")))
			agreement = __inject__(AgreementController.class).readBySystemIdentifier(Faces.getRequestParameter("agreement"));
		if(StringHelper.isNotBlank(Faces.getRequestParameter("product")))
			product = __inject__(ProductController.class).readBySystemIdentifier(Faces.getRequestParameter("product"));
		if(StringHelper.isNotBlank(Faces.getRequestParameter("truck")))
			truck = __inject__(TruckController.class).readBySystemIdentifier(Faces.getRequestParameter("truck"));
	}
	
	@Override
	protected void __process__(Collection<Delivery> __deliveries__) {
		//TODO those data should be treated from persistence request
		Agreement agreement = this.agreement;
		Product product = this.product;		
		if(agreement != null && product != null) {
			__populateDeliveries__(__deliveries__, agreement, product);
		}			
	}
	
	protected void __populateDeliveries__(Collection<Delivery> __deliveries__,Agreement agreement,Product product) {
		if(CollectionHelper.isEmpty(__deliveries__) || agreement == null || product == null)
			return;		
		for(Delivery delivery : __deliveries__) {
			if(delivery.getAgreement().equals(agreement) && delivery.getTaskAt(1) != null && delivery.getTaskAt(1).getProduct().equals(product)) {
				if(deliveries == null)
					deliveries = new ArrayList<>();
				deliveries.add(delivery);
			}		
		}			
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		StringBuilder stringBuilder = new StringBuilder("Liste des livraisons");
		if(truck == null) {
			if(agreement != null)
				stringBuilder.append(" - Contrat N° "+agreement.getCode());			
		}else {
			stringBuilder.append(" - Camion "+truck.getCode());
		}
		if(product != null)
			stringBuilder.append(" - Produit : "+product.getName());				
		return stringBuilder.toString();
	}
}
