package com.scandihealth.olympicsc.classification.controller;

import com.scandihealth.olympicsc.classification.model.Classification;
import com.scandihealth.olympicsc.classification.model.ClassificationType;
import com.scandihealth.olympicsc.data.DataManager;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.datamodel.DataModel;

import java.util.List;

@Name("classificationController")
public class ClassificationController {

    enum ClassificationTypes {
        BOOLEAN, SELECTION, TEXT
    }

    @In(create = true)
    private Classification classification;

    @DataModel("classificationTypes")
    List<ClassificationType> classificationTypes;

    @Factory("classificationTypes")
    public void findClassificationTypes() {
        DataManager dataManager = new DataManager();
        classificationTypes = dataManager.getClassificationTypes();
    }

    public String createClassification() {
        if (classification != null) {
            if (classification.getType().getType().equals(ClassificationTypes.BOOLEAN.name().toLowerCase())) {
                System.out.println("got boolean classification type");
                System.out.println("classification name: " + classification.getName());
            } else if (classification.getType().getType().equals(ClassificationTypes.SELECTION.name().toLowerCase())) {
                System.out.println("got dropdown classification type");
                System.out.println("classification name: " + classification.getName());
            }else if (classification.getType().getType().equals(ClassificationTypes.TEXT.name().toLowerCase())) {
                System.out.println("got text classification type");
                System.out.println("classification name: " + classification.getName());
            } else {
                System.out.println("did not find classification type, result was: " + classification.getType().getType());
            }
        }
        return null;
    }
}
