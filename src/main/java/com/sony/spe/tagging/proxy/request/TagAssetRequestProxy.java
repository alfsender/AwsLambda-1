package com.sony.spe.tagging.proxy.request;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tthakkar on 9/26/17.
 */
public class TagAssetRequestProxy implements Serializable {

    private String itemId;

    private String mediaType;

    private String opUnit;

    private String territory;

    private String companySystemName;

    private List<TagAssetFileProxy> files;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getOpUnit() {
        return opUnit;
    }

    public void setOpUnit(String opUnit) {
        this.opUnit = opUnit;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getCompanySystemName() {
        return companySystemName;
    }

    public void setCompanySystemName(String companySystemName) {
        this.companySystemName = companySystemName;
    }

    public List<TagAssetFileProxy> getFiles() {
        return files;
    }

    public void setFiles(List<TagAssetFileProxy> files) {
        this.files = files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagAssetRequestProxy that = (TagAssetRequestProxy) o;

        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (mediaType != null ? !mediaType.equals(that.mediaType) : that.mediaType != null) return false;
        if (opUnit != null ? !opUnit.equals(that.opUnit) : that.opUnit != null) return false;
        if (territory != null ? !territory.equals(that.territory) : that.territory != null) return false;
        if (companySystemName != null ? !companySystemName.equals(that.companySystemName) : that.companySystemName != null)
            return false;
        return files != null ? files.equals(that.files) : that.files == null;
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (mediaType != null ? mediaType.hashCode() : 0);
        result = 31 * result + (opUnit != null ? opUnit.hashCode() : 0);
        result = 31 * result + (territory != null ? territory.hashCode() : 0);
        result = 31 * result + (companySystemName != null ? companySystemName.hashCode() : 0);
        result = 31 * result + (files != null ? files.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TagAssetRequestProxy{" +
                "itemId='" + itemId + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", opUnit='" + opUnit + '\'' +
                ", territory='" + territory + '\'' +
                ", companySystemName='" + companySystemName + '\'' +
                ", files=" + files +
                '}';
    }
}
