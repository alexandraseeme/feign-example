package my.company.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ResponseMessage {

    private Integer code;
    private String type;
    private String message;

    public ResponseMessage() {
    }

    /**
     * @param message
     * @param code
     * @param type
     */
    public ResponseMessage(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    /**
     * @return The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
