package org.cyk.system.tramlop.client.controller.impl;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.system.tramlop.client.controller.entities.AbstractPersonImpl;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Contact;
import org.cyk.system.tramlop.client.controller.entities.Person;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.klass.ClassHelper;
import org.cyk.utility.__kernel__.system.action.SystemAction;
import org.cyk.utility.__kernel__.system.action.SystemActionList;
import org.cyk.utility.__kernel__.system.action.SystemActionUpdate;
import org.cyk.utility.client.controller.AbstractSystemActionFieldsNamesGetterImpl;

@org.cyk.system.tramlop.server.annotation.System
public class SystemActionFieldsNamesGetterImpl extends AbstractSystemActionFieldsNamesGetterImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Collection<String> __get__(Class<? extends SystemAction> systemActionClass, Object systemActionIdentifier,Class<?> entityClass) {
		if(Agreement.class.equals(entityClass)) {
			if(ClassHelper.isInstanceOf(systemActionClass, SystemActionList.class))
				return CollectionHelper.listOf(Agreement.FIELD_CODE,Agreement.FIELD_CUSTOMER,Agreement.FIELD_DEPARTURE_PLACE);
		}
		/*
		if(Driver.class.equals(entityClass) && ClassHelper.isInstanceOf(systemActionClass, SystemActionList.class))
			return List.of(Driver.FIELD_CODE,Driver.FIELD_FIRST_NAME,Driver.FIELD_LAST_NAMES,Driver.FIELD_PHONE_NUMBER);
		*/
		if(ClassHelper.isInstanceOf(entityClass, AbstractPersonImpl.class)) {
			if(ClassHelper.isInstanceOf(systemActionClass, SystemActionList.class))
				return CollectionHelper.listOf(AbstractPersonImpl.FIELD_CODE,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_FIRST_NAME,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_LAST_NAMES
						,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_CONTACT+"."+Contact.FIELD_PHONE_NUMBER);
			
			if(ClassHelper.isInstanceOf(systemActionClass, SystemActionUpdate.class))
				return CollectionHelper.listOf(AbstractPersonImpl.FIELD_CODE,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_FIRST_NAME,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_LAST_NAMES
						,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_IDENTITY_CARD
						,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_CONTACT+"."+Contact.FIELD_PHONE_NUMBER,AbstractPersonImpl.FIELD_PERSON+"."+Person.FIELD_CONTACT+"."+Contact.FIELD_ELECTRONIC_MAIL_ADDRESS);
				
		}
		return super.__get__(systemActionClass, systemActionIdentifier, entityClass);
	}
	
}
