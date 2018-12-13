package my.company.api.model;

import com.google.gson.annotations.SerializedName;
import feign.Param;

public enum StatusEnum {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
