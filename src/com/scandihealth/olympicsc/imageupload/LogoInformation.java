//package com.scandihealth.olympicsc.imageupload;
//
//import org.hibernate.validator.NotNull;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
//@Entity
//@Table(name = "logoinfo")
//public class LogoInformation implements Serializable {
//
//    public static final long serialVersionUID = 596009789005L;
//
//    @Id
//    @GeneratedValue
//    @Column(name = "idlogoinfo")
//    private int id;
//
//    @NotNull
//    @Column(name = "file_name", unique = true)
//    private String fileName;
//
//    @NotNull
//    @Column(name = "content_type")
//    private String contentType;
//
//    @NotNull
//    @Column(name = "upload_date")
//    private Date uploadDate;
//
//    @NotNull
//    private String title;
//
//    @NotNull
//    @Column(name = "hitcount")
//    private int hitCount;
//
//    @OneToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "logo_id")
//    private Logo artwork;
//
//    @OneToOne(fetch= FetchType.LAZY)
//    @JoinColumn(name = "thumbnail_id")
//    private Thumbnail thumbnail;
//
////    @ManyToOne(fetch=FetchType.LAZY)
////    @JoinColumn(name = "uploader")
////    private Member uploader;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
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
//    public String getContentType() {
//        return contentType;
//    }
//
//    public void setContentType(String contentType) {
//        this.contentType = contentType;
//    }
//
//    public Date getUploadDate() {
//        return uploadDate;
//    }
//
//    public void setUploadDate(Date uploadDate) {
//        this.uploadDate = uploadDate;
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
//    public int getHitCount() {
//        return hitCount;
//    }
//
//    public void setHitCount(int hitCount) {
//        this.hitCount = hitCount;
//    }
//
//    public Logo getArtwork() {
//        return artwork;
//    }
//
//    public void setArtwork(Logo artwork) {
//        this.artwork = artwork;
//    }
//
//    public Thumbnail getThumbnail() {
//        return thumbnail;
//    }
//
//    public void setThumbnail(Thumbnail thumbnail) {
//        this.thumbnail = thumbnail;
//    }
//
//
//    // setters/getters omitted
//}
