package org.cyk.system.tramlop.client.controller.impl.agreement;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Place;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.constant.ConstantCharacter;
import org.cyk.utility.__kernel__.field.FieldHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.omnifaces.util.Faces;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public abstract class AbstractAgreementReadPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Agreement agreement;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		agreement = __inject__(AgreementController.class).readBySystemIdentifier(Faces.getRequestParameter("entityidentifier"),new Properties()
				.setFields(StringHelper.concatenate(CollectionHelper.listOf(
						Agreement.FIELD_PRODUCTS,FieldHelper.join(Agreement.FIELD_PRODUCTS,Product.FIELD_WEIGHT_IN_KILO_GRAM)
						,Agreement.FIELD_ARRIVAL_PLACES,FieldHelper.join(Agreement.FIELD_ARRIVAL_PLACES,Place.FIELD_DURATION_IN_MINUTE)
						,Agreement.FIELD_TRUCKS
						,Agreement.FIELD_AGREEMENT_PRODUCTS,Agreement.FIELD_AGREEMENT_TRUCKS,Agreement.FIELD_AGREEMENT_ARRIVAL_PLACES
						)
						, ConstantCharacter.COMA.toString())));
	}
	
}
