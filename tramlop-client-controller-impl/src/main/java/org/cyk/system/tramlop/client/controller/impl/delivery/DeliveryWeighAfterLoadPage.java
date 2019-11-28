package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.DeliveryTaskController;
import org.cyk.system.tramlop.server.persistence.entities.Task;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DeliveryWeighAfterLoadPage extends AbstractDeliveryPage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected String __getTaskCode__() {
		return Task.CODE_PESE_CHARGE;
	}
	
	public void __save__() {
		deliveryTask.setWeightInKiloGram(delivery.getWeightInKiloGram());
		__inject__(DeliveryTaskController.class).create(deliveryTask);
	}
	
}
