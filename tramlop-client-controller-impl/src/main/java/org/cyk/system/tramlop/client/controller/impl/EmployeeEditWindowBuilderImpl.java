package org.cyk.system.tramlop.client.controller.impl;
import java.io.Serializable;
import java.util.Collection;

import org.cyk.system.tramlop.client.controller.entities.AbstractPersonImpl;
import org.cyk.system.tramlop.client.controller.entities.Contact;
import org.cyk.system.tramlop.client.controller.entities.Employee;
import org.cyk.system.tramlop.client.controller.entities.EmployeeEditWindowBuilder;
import org.cyk.system.tramlop.client.controller.entities.Person;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.system.action.SystemAction;
import org.cyk.utility.client.controller.component.view.ViewBuilder;
import org.cyk.utility.client.controller.component.window.WindowBuilder;
import org.cyk.utility.client.controller.data.Data;
import org.cyk.utility.client.controller.data.Form;

public class EmployeeEditWindowBuilderImpl extends AbstractPersonEditWindowBuilderImpl<Employee> implements EmployeeEditWindowBuilder, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __execute__(Form form, SystemAction systemAction, Data data, ViewBuilder viewBuilder) {
		super.__execute__(form, systemAction, data, viewBuilder);
		viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Employee.FIELD_NOTIFIABLE_ON_DELIVERY_DURATION_ALERT);
	}
	
	@Override
	protected Collection<String> __getPersistenceEntityFieldNames__(WindowBuilder window, SystemAction systemAction,Class<? extends Form> formClass) {
		return CollectionHelper.listOf(AbstractPersonImpl.FIELD_CODE,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_FIRST_NAME,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_LAST_NAMES
				,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_IDENTITY_CARD,Employee.FIELD_NOTIFIABLE_ON_DELIVERY_DURATION_ALERT
				,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_CONTACT+"."+Contact.FIELD_PHONE_NUMBER,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_CONTACT+"."+Contact.FIELD_ELECTRONIC_MAIL_ADDRESS);
	}
}
