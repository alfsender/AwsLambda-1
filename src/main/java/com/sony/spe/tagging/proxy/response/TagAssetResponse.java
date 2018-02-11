package com.sony.spe.tagging.proxy.response;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tthakkar on 10/30/17.
 */
public class TagAssetResponse implements Serializable {

    private String itemId;

    private Date taggedDate;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Date getTaggedDate() {
        return taggedDate;
    }

    public void setTaggedDate(Date taggedDate) {
        this.taggedDate = taggedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagAssetResponse response = (TagAssetResponse) o;

        if (itemId != null ? !itemId.equals(response.itemId) : response.itemId != null) return false;
        return taggedDate != null ? taggedDate.equals(response.taggedDate) : response.taggedDate == null;
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (taggedDate != null ? taggedDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TagAssetResponse{" +
                "itemId='" + itemId + '\'' +
                ", taggedDate=" + taggedDate +
                '}';
    }
}
