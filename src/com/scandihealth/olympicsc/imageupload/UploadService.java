//package com.scandihealth.olympicsc.imageupload;
//
//import com.scandihealth.olympicsc.data.DataManager;
//import org.jboss.seam.ScopeType;
//import org.jboss.seam.annotations.Name;
//import org.jboss.seam.annotations.Scope;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//@Name("uploadService")
//@Scope(ScopeType.SESSION)
//public class UploadService {
//    private byte[] data;
//    private String contentType;
//    private String fileName;
//    private String title;
//    private int size;
//
//
//    public byte[] getData() {
//        return data;
//    }
//
//    public void setData(byte[] data) {
//        this.data = data;
//    }
//
//    public String getContentType() {
//        return contentType;
//    }
//
//    public void setContentType(String contentType) {
//        this.contentType = contentType;
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public void upload() {
//        DataManager dataManager = new DataManager();
//        byte[] thumbnailData = this.scale();
//
//        Thumbnail thumbnail = new Thumbnail();
//        thumbnail.setData(thumbnailData);
//        thumbnail.setSize(thumbnailData.length);
//        dataManager.saveObject(thumbnail);
//
//
//        Logo logo = new Logo();
//        logo.setData(data);
//        logo.setSize(size);
//        dataManager.saveObject(logo);
//
//
//    }
//
//    private byte[] scale() {
//        InputStream inputStream = new ByteArrayInputStream(data);
//        BufferedImage uploadedImg = null;
//        try {
//            uploadedImg = ImageIO.read(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        int type = (uploadedImg.getTransparency() == Transparency.OPAQUE) ?
//                BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
//
//        /*
//        * The uploaded image has to be passed to drawImage at the first
//        * iteration only. For the remaining iterations, the newly scaled image
//        * has to be passed instead. If you find this assignment a bit confusing
//        * you can always assign it a null and then add an if statement similar
//        * to the scratchImage below.
//        */
//        BufferedImage scaledImage = uploadedImg;
//        BufferedImage scratchImage = null;
//        Graphics2D g2 = null;
//
//        int w = uploadedImg.getWidth();
//        int h = uploadedImg.getHeight();
//        int prevW = scaledImage.getWidth();
//        int prevH = scaledImage.getHeight();
//
//        // the default with of the thumbnail is set to 200
//        int targetWidth = 200;
//        double ratio = (double) targetWidth / w;
//        int targetHeight = (int) (h * ratio);
//
//        Object hint = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
//
//        do {
//            if (w > targetWidth) {
//                w /= 2;
//                if (w < targetWidth) {
//                    w = targetWidth;
//                }
//            }
//            if (h > targetHeight) {
//                h /= 2;
//                if (h < targetHeight) {
//                    h = targetHeight;
//                }
//            }
//            if (scratchImage == null) {
//                // Use a single scratch buffer for all iterations
//                // and then copy to the final, correctly sized image
//                // before returning
//                scratchImage = new BufferedImage(w, h, type);
//                g2 = scratchImage.createGraphics();
//            }
//            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
//            g2.drawImage(scaledImage, 0, 0, w, h, 0, 0, prevW, prevH, null);
//            prevW = w;
//            prevH = h;
//            scaledImage = scratchImage;
//        } while (w != targetWidth || h != targetHeight);
//        if (g2 != null) {
//            g2.dispose();
//        }
//        // If we used a scratch buffer that is larger than our
//        // target size, create an image of the right size and copy
//        // the results into it
//        if (targetWidth != scaledImage.getWidth() || targetHeight != scaledImage.getHeight()) {
//            scratchImage = new BufferedImage(targetWidth, targetHeight, type);
//            g2 = scratchImage.createGraphics();
//            g2.drawImage(scaledImage, 0, 0, null);
//            g2.dispose();
//            scaledImage = scratchImage;
//        }
//
//        String formatName = "";
//        if ("image/png".equals(contentType)) {
//            formatName = "png";
//        } else if ("image/jpeg".equals(contentType)) {
//            formatName = "jpeg";
//        }
//
//        // convert the resulting image into an array of bytes
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(scaledImage, formatName, outputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return outputStream.toByteArray();
//    }
//
//}
