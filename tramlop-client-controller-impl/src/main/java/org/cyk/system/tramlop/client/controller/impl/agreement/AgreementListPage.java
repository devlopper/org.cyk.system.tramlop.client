package org.cyk.system.tramlop.client.controller.impl.agreement;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class AgreementListPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Collection<Agreement> agreements;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		agreements = __inject__(AgreementController.class).read(new Properties().setIsPageable(Boolean.FALSE));
	}
	
}
