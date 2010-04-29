package com.scandihealth.olympicsc.imageupload;

import org.jboss.seam.annotations.Name;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Name("thumbnail")
@Table(name = "logothumb", catalog = "olympicsc")
public class Thumbnail implements Serializable {

    public static final long serialVersionUID = 596009789003L;

    @Id
    @GeneratedValue
    @Column(name = "idlogothumb")
    private int id;

    // the persistence manager ignores the fetch property.
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "data")
    private byte[] data;

    @Column(name = "size")
    private long size;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}