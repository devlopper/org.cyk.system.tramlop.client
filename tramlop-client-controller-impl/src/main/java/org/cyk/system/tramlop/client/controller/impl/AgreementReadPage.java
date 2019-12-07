package org.cyk.system.tramlop.client.controller.impl;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.constant.ConstantCharacter;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.omnifaces.util.Faces;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class AgreementReadPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Agreement agreement;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		agreement = __inject__(AgreementController.class).readBySystemIdentifier(Faces.getRequestParameter("entityidentifier"),new Properties()
				.setFields(StringHelper.concatenate(CollectionHelper.listOf(Agreement.FIELD_PRODUCTS,Agreement.FIELD_ARRIVAL_PLACES,Agreement.FIELD_TRUCKS)
						, ConstantCharacter.COMA.toString())));
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Contrat N° "+agreement.getCode();
	}
}
