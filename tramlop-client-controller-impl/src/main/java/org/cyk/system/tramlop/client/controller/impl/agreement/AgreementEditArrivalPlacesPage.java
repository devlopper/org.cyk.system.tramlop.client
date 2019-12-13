package org.cyk.system.tramlop.client.controller.impl.agreement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Place;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.identifier.resource.UniformResourceIdentifierHelper;
import org.cyk.utility.__kernel__.instance.SelectionMany;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.system.action.SystemActionCustom;
import org.cyk.utility.__kernel__.system.action.SystemActionRead;
import org.cyk.utility.client.controller.component.command.Commandable;
import org.cyk.utility.client.controller.component.command.CommandableBuilder;
import org.cyk.utility.client.controller.component.window.WindowBuilder;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class AgreementEditArrivalPlacesPage extends AbstractAgreementReadPage implements Serializable {
	private static final long serialVersionUID = 1L;

	private SelectionMany<Place> arrivalPlaces;
	private Commandable addArrivalPlaceCommandable;
	private Commandable updateCommandable;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		try {
			arrivalPlaces = new SelectionMany<Place>(Place.class);
			if(CollectionHelper.isNotEmpty(agreement.getArrivalPlaces()) && CollectionHelper.isNotEmpty(arrivalPlaces.getChoices())) {
				Collection<Place> selected = null;
				for(Place place : agreement.getArrivalPlaces())
					for(Place choice : arrivalPlaces.getChoices())
						if(place.equals(choice)) {
							choice.setDurationInMinute(place.getDurationInMinute());
							if(selected == null)
								selected = new ArrayList<>();
							selected.add(choice);
							break;
						}
				if(CollectionHelper.isNotEmpty(selected))
					arrivalPlaces.select(selected);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CommandableBuilder addPlaceCommandableBuilder = __inject__(CommandableBuilder.class);
		addPlaceCommandableBuilder.setName("Ajouter").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					arrivalPlaces.select();
				}
			}
		);
		addPlaceCommandableBuilder.getCommand().getFunction().setIsNotifyOnThrowableIsNull(Boolean.FALSE);
		addPlaceCommandableBuilder.addUpdatables("form:arrivalPlace,arrivalPlaces");
		addArrivalPlaceCommandable = addPlaceCommandableBuilder.execute().getOutput();
		
		CommandableBuilder updateCommandableBuilder = __inject__(CommandableBuilder.class);
		updateCommandableBuilder.setName("Enregistrer").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					update();
				}
			}
		);
		updateCommandable = updateCommandableBuilder.execute().getOutput();
	}
	
	public void update() {
		agreement.setArrivalPlaces((List<Place>) arrivalPlaces.getValue());
		__inject__(AgreementController.class).update(agreement,new Properties().setFields(Agreement.FIELD_ARRIVAL_PLACES));
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Lieux de déchargement du contrat N° "+agreement.getCode();
	}
	
	@Override
	protected String __processWindowDialogOkCommandableGetUrl__(WindowBuilder window, CommandableBuilder commandable) {
		Collection entities = List.of(agreement);
		return UniformResourceIdentifierHelper.build(__getRequest__(), SystemActionRead.class, null, Agreement.class, entities, null, null, null);
	}
}
