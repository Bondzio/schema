package com.scandihealth.olympicsc.customvalues.controller;

import com.scandihealth.olympicsc.customvalues.model.ValueTypes;
import com.scandihealth.olympicsc.data.DataManager;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.datamodel.DataModel;

import java.util.List;

@Name("customValuesController")
public class CustomValuesController {


    @DataModel
    private List<ValueTypes> valueTypesList;

    @Factory("valueTypesList")
    public void findValueTypes() {
        System.out.println("finding value types");
        DataManager dataManager = new DataManager();
        valueTypesList = dataManager.getValueTypes();
        for (ValueTypes valueTypes : valueTypesList) {
            System.out.println("valueTypes = " + valueTypes.getType());
        }
    }

}
