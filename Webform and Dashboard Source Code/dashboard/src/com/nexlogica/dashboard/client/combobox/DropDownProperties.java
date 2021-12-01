package com.nexlogica.dashboard.client.combobox;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
 
public interface DropDownProperties extends PropertyAccess<DropDown> {
  @Path("id")
  ModelKeyProvider<DropDown> key();
   
  @Path("name")
  LabelProvider<DropDown> nameLabel();
 
  ValueProvider<DropDown, String> name();
 }