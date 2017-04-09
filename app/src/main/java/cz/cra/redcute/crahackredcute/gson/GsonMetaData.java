package cz.cra.redcute.crahackredcute.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by obrusvit on 31.8.16.
 */
public class GsonMetaData {

    static class Meta{
        private String status;
        private int count;
    }

    @SerializedName("_meta")
    Meta meta;

    public String getStatus() {
        return meta.status;
    }

    public int getCount() {
        return meta.count;
    }
}

