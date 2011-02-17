package com.scandihealth.olympicsc.classification.model;

import org.jboss.seam.annotations.Name;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@javax.persistence.Table(name = "classification", catalog = "olympicsc")
@Name("classification")
public class Classification {
    private int id;

    private ClassificationType type;
    private Set<ClassificationValue> classificationValues = new HashSet<ClassificationValue>();

    @javax.persistence.Column(name = "id")
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    @javax.persistence.Column(name = "name")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "classificationTypeId")
    public ClassificationType getType() {
        return type;
    }

    public void setType(ClassificationType type) {
        this.type = type;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "classification_has_value",
            joinColumns = {@JoinColumn(name = "id_classification")},
            inverseJoinColumns = {@JoinColumn(name = "id_classificationValue")})
    public Set<ClassificationValue> getClassificationValues() {
        return classificationValues;
    }

    public void setClassificationValues(Set<ClassificationValue> classificationValues) {
        this.classificationValues = classificationValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classification that = (Classification) o;

        if (type != that.getType()) return false;
        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + type.hashCode();
        return result;
    }

    public void addValue(ClassificationValue value) {
        classificationValues.add(value);
    }

    public void removeValue(ClassificationValue value) {
        classificationValues.remove(value);
    }
}
