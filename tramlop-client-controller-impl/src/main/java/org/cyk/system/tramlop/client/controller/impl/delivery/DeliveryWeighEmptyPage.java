package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryWeighEmptyPage extends AbstractDeliveryPage implements Serializable {
	private static final long serialVersionUID = 1L;

	public void create() {
		
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Pesée de camion vide";
	}
	
}
