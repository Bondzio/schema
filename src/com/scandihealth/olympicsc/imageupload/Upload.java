package com.scandihealth.olympicsc.imageupload;

public interface Upload {
    byte[] getData();

    void setData(byte[] data);

    String getContentType();

    void setContentType(String contentType);

    String getFileName();

    void setFileName(String fileName);

    String getTitle();

    void setTitle(String title);

    int getSize();

    void setSize(int size);

    void upload();

}
