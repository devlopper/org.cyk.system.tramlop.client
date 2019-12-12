package org.cyk.system.tramlop.client.controller.impl.agreement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.system.tramlop.client.controller.api.AgreementController;
import org.cyk.system.tramlop.client.controller.entities.Agreement;
import org.cyk.system.tramlop.client.controller.entities.Product;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.identifier.resource.UniformResourceIdentifierHelper;
import org.cyk.utility.__kernel__.instance.SelectionMany;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.system.action.SystemActionCustom;
import org.cyk.utility.__kernel__.system.action.SystemActionList;
import org.cyk.utility.__kernel__.system.action.SystemActionRead;
import org.cyk.utility.client.controller.component.command.Commandable;
import org.cyk.utility.client.controller.component.command.CommandableBuilder;
import org.cyk.utility.client.controller.component.window.WindowBuilder;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class AgreementEditProductsPage extends AbstractAgreementReadPage implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer productWeightInKiloGram;
	private SelectionMany<Product> products;
	private Commandable addProductCommandable;
	private Commandable updateCommandable;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		try {
			products = new SelectionMany<Product>(Product.class);
			if(CollectionHelper.isNotEmpty(agreement.getProducts()) && CollectionHelper.isNotEmpty(products.getChoices())) {
				Collection<Product> selected = null;
				for(Product product : agreement.getProducts())
					for(Product choice : products.getChoices())
						if(product.equals(choice)) {
							choice.setWeightInKiloGram(product.getWeightInKiloGram());
							if(selected == null)
								selected = new ArrayList<>();
							selected.add(choice);
							break;
						}
				if(CollectionHelper.isNotEmpty(selected))
					products.select(selected);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CommandableBuilder addProductCommandableBuilder = __inject__(CommandableBuilder.class);
		addProductCommandableBuilder.setName("Ajouter").setCommandFunctionActionClass(SystemActionCustom.class).addCommandFunctionTryRunRunnable(
			new Runnable() {
				@Override
				public void run() {
					products.select();
				}
			}
		);
		addProductCommandableBuilder.getCommand().getFunction().setIsNotifyOnThrowableIsNull(Boolean.FALSE);
		addProductCommandableBuilder.addUpdatables("form:product,products");
		addProductCommandable = addProductCommandableBuilder.execute().getOutput();
		
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
		agreement.setProducts((List<Product>) products.getValue());
		__inject__(AgreementController.class).update(agreement,new Properties().setFields(Agreement.FIELD_PRODUCTS));
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Produits du contrat N° "+agreement.getCode();
	}
	
	@Override
	protected String __processWindowDialogOkCommandableGetUrl__(WindowBuilder window, CommandableBuilder commandable) {
		Collection entities = List.of(agreement);
		return UniformResourceIdentifierHelper.build(__getRequest__(), SystemActionRead.class, null, Agreement.class, entities, null, null, null);
	}
}
