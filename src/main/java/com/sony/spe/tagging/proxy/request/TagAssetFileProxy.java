package com.sony.spe.tagging.proxy.request;


import java.io.Serializable;

/**
 * Created by tthakkar on 10/30/17.
 */
public class TagAssetFileProxy implements Serializable {

    private String fileName;

    private Boolean canBeArchived;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getCanBeArchived() {
        return canBeArchived;
    }

    public void setCanBeArchived(Boolean canBeArchived) {
        this.canBeArchived = canBeArchived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagAssetFileProxy that = (TagAssetFileProxy) o;

        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        return canBeArchived != null ? canBeArchived.equals(that.canBeArchived) : that.canBeArchived == null;
    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (canBeArchived != null ? canBeArchived.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TagAssetFileProxy{" +
                "fileName='" + fileName + '\'' +
                ", canBeArchived=" + canBeArchived +
                '}';
    }
}
