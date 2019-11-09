package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.cyk.utility.client.controller.component.annotation.Input;
import org.cyk.utility.client.controller.component.annotation.InputChoice;
import org.cyk.utility.client.controller.component.annotation.InputChoiceOne;
import org.cyk.utility.client.controller.component.annotation.InputChoiceOneCombo;
import org.cyk.utility.client.controller.component.annotation.InputDateTime;
import org.cyk.utility.client.controller.component.annotation.InputString;
import org.cyk.utility.client.controller.component.annotation.InputStringLineOne;
import org.cyk.utility.client.controller.data.AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Shipment extends AbstractDataIdentifiableSystemStringIdentifiableBusinessStringImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	/*
	@Input @InputChoice @InputChoiceOne @InputChoiceOneCombo
	@NotNull
	private Loading loading;
	*/
	@Input @InputString @InputStringLineOne
	@NotNull
	private String placeOfDeparture;
	
	@Input @InputString @InputStringLineOne
	@NotNull
	private String placeOfArrival;
	
	@Input @InputDateTime
	@NotNull
	private LocalDateTime dateOfDeparture;
	
	@Input @InputDateTime
	@NotNull
	private LocalDateTime dateOfArrival;
	
	//@Input @InputString @InputStringLineOne
	//@NotNull
	private String duration;
	
	private String status;
	
	/**/
	
	public static final String FIELD_LOADING = "loading";
	public static final String FIELD_PLACE_OF_DEPARTURE = "placeOfDeparture";
	public static final String FIELD_PLACE_OF_ARRIVAL = "placeOfArrival";
	public static final String FIELD_DATE_OF_DEPARTURE = "dateOfDeparture";
	public static final String FIELD_DATE_OF_ARRIVAL = "dateOfArrival";
	public static final String FIELD_DURATION = "duration";
	public static final String FIELD_STATUS = "status";
}
