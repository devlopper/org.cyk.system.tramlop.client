package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.system.tramlop.client.controller.api.TruckController;
import org.cyk.system.tramlop.client.controller.entities.Truck;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.system.action.SystemActionCustom;
import org.cyk.utility.client.controller.component.command.Commandable;
import org.cyk.utility.client.controller.component.command.CommandableBuilder;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AbstractDeliveryPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Commandable saveCommandable;
	protected Collection<Truck> trucks;
	protected Truck truck;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		try {
			trucks = __inject__(TruckController.class).read(new Properties().setIsPageable(Boolean.FALSE));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommandableBuilder saveCommandableBuilder = __inject__(CommandableBuilder.class);
		saveCommandableBuilder.setName("Enregistrer").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					create();
				}
			}
		);
		saveCommandable = saveCommandableBuilder.execute().getOutput();
	}
	
	public void create() {
		
	}
	
}
