package com.scandihealth.olympicsc.classification.model;

import org.jboss.seam.annotations.Name;

@Name("classification")
public class Classification {
    private Object name;
    private ClassificationType type;

    public void setName(Object name) {
        this.name = name;
    }

    public Object getName() {
        return name;
    }

    public void setType(ClassificationType type) {
        this.type = type;
    }

    public ClassificationType getType() {
        return type;
    }
}
