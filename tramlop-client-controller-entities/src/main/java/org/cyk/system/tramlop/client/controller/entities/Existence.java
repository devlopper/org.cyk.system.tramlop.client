package org.cyk.system.tramlop.client.controller.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Existence implements Serializable {
	private static final long serialVersionUID = 1L;

	private String createdBy;
	private String creationDate;
	private String updateDate;
	
	/**/
	
	public static final String FIELD_CREATED_BY = "createdBy";
	public static final String FIELD_CREATION_DATE = "creationDate";
	public static final String FIELD_UPDATE_DATE = "updateDate";
}
