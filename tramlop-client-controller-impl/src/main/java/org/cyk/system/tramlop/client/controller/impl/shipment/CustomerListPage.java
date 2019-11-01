package org.cyk.system.tramlop.client.controller.impl.shipment;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.entities.shipment.Customer;
import org.cyk.utility.__kernel__.system.action.SystemActionList;
import org.cyk.utility.client.controller.component.window.WindowContainerManagedWindowBuilder;
import org.cyk.utility.client.controller.component.window.WindowContainerManagedWindowBuilderGetter;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class CustomerListPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	protected WindowContainerManagedWindowBuilder __getWindowContainerManagedWindowBuilder__() {
		return __inject__(WindowContainerManagedWindowBuilderGetter.class).setContainerManaged(this)
				.setSystemAction(__inject__(SystemActionList.class).setEntityClass(Customer.class))
				.execute().getOutput();
	}
	
}