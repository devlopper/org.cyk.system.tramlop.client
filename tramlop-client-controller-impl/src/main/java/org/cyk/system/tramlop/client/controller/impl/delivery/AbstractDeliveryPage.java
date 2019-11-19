package org.cyk.system.tramlop.client.controller.impl.delivery;

import java.io.Serializable;

import org.cyk.system.tramlop.client.controller.entities.SelectionOneTruck;
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
	protected SelectionOneTruck truck;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		try {
			truck = __getTrucks__();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		CommandableBuilder saveCommandableBuilder = __inject__(CommandableBuilder.class);
		saveCommandableBuilder.setName("Enregistrer").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					__save__();
				}
			}
		);
		saveCommandable = saveCommandableBuilder.execute().getOutput();
	}
	
	protected SelectionOneTruck __getTrucks__() {
		return new SelectionOneTruck(__getReadTrucksProperties__());
	}
	
	protected Properties __getReadTrucksProperties__() {
		return new Properties().setIsPageable(Boolean.FALSE);
	}
	
	protected void __save__() {
		
	}
	
}
