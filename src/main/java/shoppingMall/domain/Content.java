package shoppingMall.domain;


import java.time.LocalDateTime;

public class Content {
    private String idFile;
    private String originalFileName;
    private String savedFileName;
    private String filePath;
    private String fileExt;
    private String fileType;
    private String serviceId;
    private String noRegister;
    private LocalDateTime firstDate;

    public Content() {
    }

    public Content(String idFile, String originalFileName, String savedFileName, String filePath, String fileExt, String fileType, String serviceId, String noRegister, LocalDateTime firstDate) {
        this.idFile = idFile;
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
        this.filePath = filePath;
        this.fileExt = fileExt;
        this.fileType = fileType;
        this.serviceId = serviceId;
        this.noRegister = noRegister;
        this.firstDate = firstDate;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getSavedFileName() {
        return savedFileName;
    }

    public void setSavedFileName(String savedFileName) {
        this.savedFileName = savedFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getNoRegister() {
        return noRegister;
    }

    public void setNoRegister(String noRegister) {
        this.noRegister = noRegister;
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDateTime firstDate) {
        this.firstDate = firstDate;
    }
}
