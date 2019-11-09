package org.cyk.system.tramlop.client.controller.api;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.utility.client.controller.AbstractControllerEntityImpl;

@ApplicationScoped
public class AgreementControllerImpl extends AbstractControllerEntityImpl<Agreement> implements AgreementController,Serializable {
	private static final long serialVersionUID = 1L;
	
}
