package com.scandihealth.olympicsc.imageupload.controller;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.imageupload.model.Logo;
import com.scandihealth.olympicsc.imageupload.model.LogoRepository;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;

import java.util.List;

@Name("imageUploader")
@Scope(ScopeType.CONVERSATION)
public class ImageUploader {
    private byte[] data;
    private String contentType;
    private String fileName;
    private String title;
    private int size;
    private LogoRepository logoRepository;
    @DataModel
    private List<Logo> logoList;


    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String save() {
        DataManager dataManager = new DataManager();

        Logo logo = new Logo();
        logo.setName(getFileName());
        logo.setData(getData());
        logo.setContentType(getContentType());
        logo.setSize(getSize());

        dataManager.saveObject(logo);
        return "";
    }

    @Factory("logoList")
    public void findLocations() {
        if (logoRepository == null) {
            logoRepository = new LogoRepository();
        }
        logoList = logoRepository.getLogoList();
    }
}
