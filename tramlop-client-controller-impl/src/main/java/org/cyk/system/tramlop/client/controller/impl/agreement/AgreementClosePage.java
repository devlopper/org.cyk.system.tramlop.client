package org.cyk.system.tramlop.client.controller.impl.agreement;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.system.action.SystemActionCustom;
import org.cyk.utility.client.controller.component.command.Commandable;
import org.cyk.utility.client.controller.component.command.CommandableBuilder;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.omnifaces.util.Faces;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class AgreementClosePage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Agreement agreement;
	private Commandable closeCommandable;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		agreement = __inject__(AgreementController.class).readBySystemIdentifier(Faces.getRequestParameter("entityidentifier"));
		
		CommandableBuilder closeCommandableBuilder = __inject__(CommandableBuilder.class);
		closeCommandableBuilder.setName("Clore le contrat").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					close();
				}
			}
		);
		closeCommandable = closeCommandableBuilder.execute().getOutput();
	}
	
	public void close() {
		agreement.setClosed(Boolean.TRUE);
		__inject__(AgreementController.class).update(agreement,new Properties().setFields(Agreement.FIELD_CLOSED));
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Clotûre du contrat N° "+agreement.getCode();
	}
	
}
