package org.cyk.system.tramlop.client.controller.api;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.AgreementProduct;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class AgreementProductControllerImpl extends AbstractControllerEntityImpl<AgreementProduct> implements AgreementProductController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
