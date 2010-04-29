package com.scandihealth.olympicsc.imageupload;

public class WebUtil {
    public static String getFileName(String path) {
        // Path format is "/file_name.jpg"
        if (path != null && path.length() > 1) {
            String fileName = path.substring(1);
            int dotIndex = fileName.indexOf('.');
            String extention = fileName.substring(dotIndex + 1);
            if (extention.equals(Extention.jpg.getExtention())
                    || extention.equals(Extention.png.getExtention())
                    || extention.equals(Extention.gif.getExtention())) {
                return fileName;
            }
        }
        return null;
    }

    enum Extention {
        jpg("jpg"), png("png"), gif("gif");

        Extention(String extention) {
            this.extention = extention;
        }

        private String extention;

        String getExtention() {
            return this.extention;
        }
    }
}
