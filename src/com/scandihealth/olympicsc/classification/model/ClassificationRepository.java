package com.scandihealth.olympicsc.classification.model;

import com.scandihealth.olympicsc.data.DataManager;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.util.ArrayList;
import java.util.List;

@Name("classificationRepository")
@Scope(ScopeType.SESSION)
public class ClassificationRepository {
    List<Classification> classifications;

    public ClassificationRepository() {
        DataManager dataManager = new DataManager();
        classifications = dataManager.getClassifications();
        if (classifications == null) {
            classifications = new ArrayList<Classification>();
        }
    }

    public boolean addClassification(Classification classification) {
        return !classifications.contains(classification) && classifications.add(classification);
    }

    public void removeClassification(Classification classification) {
        classifications.remove(classification);
    }

    public List<Classification> getClassifications() {
        return classifications;
    }


}