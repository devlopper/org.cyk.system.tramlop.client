package org.cyk.system.tramlop.client.controller.impl;
import java.io.Serializable;
import java.util.Collection;

import org.cyk.system.tramlop.client.controller.entities.AbstractPersonImpl;
import org.cyk.system.tramlop.client.controller.entities.Contact;
import org.cyk.system.tramlop.client.controller.entities.Customer;
import org.cyk.system.tramlop.client.controller.entities.Person;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.system.action.SystemAction;
import org.cyk.utility.__kernel__.system.action.SystemActionCreate;
import org.cyk.utility.client.controller.component.view.ViewBuilder;
import org.cyk.utility.client.controller.component.window.AbstractWindowContainerManagedWindowBuilderEditDataImpl;
import org.cyk.utility.client.controller.component.window.WindowBuilder;
import org.cyk.utility.client.controller.data.Data;
import org.cyk.utility.client.controller.data.Form;

public abstract class AbstractPersonEditWindowBuilderImpl<PERSON extends AbstractPersonImpl> extends AbstractWindowContainerManagedWindowBuilderEditDataImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __execute__(Form form,SystemAction systemAction,Data data,ViewBuilder viewBuilder) {
		@SuppressWarnings("unchecked")
		PERSON person = (PERSON) data;
		viewBuilder.addInputBuilderByObjectByFieldNames(data,systemAction, Customer.FIELD_CODE);
		viewBuilder.addInputBuilderByObjectByFieldNames(person.getPerson(),systemAction,Person.FIELD_FIRST_NAME);
		viewBuilder.addInputBuilderByObjectByFieldNames(person.getPerson(),systemAction,Person.FIELD_LAST_NAMES);
		viewBuilder.addInputBuilderByObjectByFieldNames(person.getPerson(),systemAction,Person.FIELD_IDENTITY_CARD);
		viewBuilder.addInputBuilderByObjectByFieldNames(person.getPerson().getContact(Boolean.TRUE),systemAction, Contact.FIELD_ELECTRONIC_MAIL_ADDRESS);
		viewBuilder.addInputBuilderByObjectByFieldNames(person.getPerson().getContact(Boolean.TRUE),systemAction, Contact.FIELD_PHONE_NUMBER);
	}

	@Override
	protected Object __getEntity__(SystemAction systemAction) {
		@SuppressWarnings("unchecked")
		PERSON person = (PERSON) super.__getEntity__(systemAction);
		if(systemAction instanceof SystemActionCreate) {
			person.setPerson(new Person().setContact(new Contact()));
		}
		return person;
	}
	
	@Override
	protected Collection<String> __getPersistenceEntityFieldNames__(WindowBuilder window, SystemAction systemAction,Class<? extends Form> formClass) {
		return CollectionHelper.listOf(AbstractPersonImpl.FIELD_CODE,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_FIRST_NAME,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_LAST_NAMES
				,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_IDENTITY_CARD
				,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_CONTACT+"."+Contact.FIELD_PHONE_NUMBER,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_CONTACT+"."+Contact.FIELD_ELECTRONIC_MAIL_ADDRESS);
	}
}
