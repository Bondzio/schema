package com.scandihealth.olympicsc.imageupload.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "logo")
public class Logo implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "idLogo")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "size")
    private long size;
    @Column(name = "contenttype")
    private String contentType;
    @Lob
    @Column(length = 2147483647)
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }


    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logo logo = (Logo) o;

        if (size != logo.size) return false;
        if (contentType != null ? !contentType.equals(logo.contentType) : logo.contentType != null) return false;
        if (!Arrays.equals(data, logo.data)) return false;
        if (id != null ? !id.equals(logo.id) : logo.id != null) return false;
        if (name != null ? !name.equals(logo.name) : logo.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (size ^ (size >>> 32));
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + (data != null ? Arrays.hashCode(data) : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}