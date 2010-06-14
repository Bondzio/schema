package com.scandihealth.olympicsc.customvalues.controller;

import com.scandihealth.olympicsc.customvalues.model.CustomValueType;
import com.scandihealth.olympicsc.data.DataManager;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;

import java.util.List;


@Name("customValueController")
@Scope(ScopeType.SESSION)
public class CustomValueController {

    @DataModel("customValueTypes")
    private List<CustomValueType> customValueTypeList;


    @Factory("customValueTypes")
    public void findCustomValueTypes() {
        DataManager dataManager = new DataManager();
        customValueTypeList = dataManager.getCustomValueTypes();
    }

    
}
