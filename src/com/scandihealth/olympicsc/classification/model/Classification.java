package com.scandihealth.olympicsc.classification.model;

import org.jboss.seam.annotations.Name;

import javax.persistence.*;
import java.util.Set;

@Entity
@javax.persistence.Table(name = "classification", catalog = "olympicsc")
@Name("classification")
public class Classification {
    private String name;
    private ClassificationType type;

    private Set<ClassificationValue> classificationValues;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setType(ClassificationType type) {
        this.type = type;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "classificationTypeId")
    public ClassificationType getType() {
        return type;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "classification_has_value",
            joinColumns = {@JoinColumn(name = "id_classification")},
            inverseJoinColumns = {@JoinColumn(name = "id_classificationValue")})
    public Set<ClassificationValue> getClassificationValues() {
        return classificationValues;
    }

    public void setClassificationValues(Set<ClassificationValue> classificationValues) {
        this.classificationValues = classificationValues;
    }
}
