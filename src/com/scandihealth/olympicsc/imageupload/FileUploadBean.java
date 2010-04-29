package com.scandihealth.olympicsc.imageupload;

import com.scandihealth.olympicsc.data.DataManager;
import com.scandihealth.olympicsc.imageupload.model.Logo;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import java.io.*;
import java.util.ArrayList;

@Name("fileUploadBean")
@Scope(ScopeType.SESSION)
public class FileUploadBean {
    private ArrayList<Logo> files = new ArrayList<Logo>();
    private int uploadsAvailable = 1;
    private boolean autoUpload = true;
    private boolean useFlash = false;
    private String imageName;

    public int getSize() {
        if (getFiles().size() > 0) {
            return getFiles().size();
        } else {
            return 0;
        }
    }

    public FileUploadBean() {
    }

    public String save() {
        DataManager dataManager = new DataManager();
        if (files.size() > 0) {
            Logo logo = files.get(0);
            logo.setName(getImageName());
            dataManager.saveObject(logo);
        }
        return "";
    }

    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer) object).getData());
    }

    public void listener(UploadEvent event) throws Exception {
        UploadItem item = event.getUploadItem();
        File file = item.getFile();
        Logo logo = new Logo();


        byte[] data = getBytesFromFile(file);
        String fileName = file.getName();
        logo.setSize(data.length);
        logo.setName(fileName);
        logo.setData(data);
        files.add(logo);
        uploadsAvailable--;
    }

    public String clearUploadData() {
        files.clear();
        setUploadsAvailable(5);
        return null;
    }

    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public ArrayList<Logo> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<Logo> files) {
        this.files = files;
    }

    public int getUploadsAvailable() {
        return uploadsAvailable;
    }

    public void setUploadsAvailable(int uploadsAvailable) {
        this.uploadsAvailable = uploadsAvailable;
    }

    public boolean isAutoUpload() {
        return autoUpload;
    }

    public void setAutoUpload(boolean autoUpload) {
        this.autoUpload = autoUpload;
    }

    public boolean isUseFlash() {
        return useFlash;
    }

    public void setUseFlash(boolean useFlash) {
        this.useFlash = useFlash;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    // Returns the contents of the file in a byte array.
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}
