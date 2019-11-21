package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryReadDialogPage extends AbstractDeliveryReadPage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Boolean getIsRenderTypeDialog() {
		return Boolean.TRUE;
	}
	
}
