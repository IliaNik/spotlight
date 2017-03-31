package com.spotlight.dto.error;

import java.io.Serializable;

/**
 * @author IliaNik on 20.03.2017.
 */
public class FieldErrorDTO implements Serializable {

    private String code;
    private String message;
    private String field;

    public FieldErrorDTO() {
    }

    public FieldErrorDTO(String code, String message, String field) {
        this.code = code;
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return field;
    }

    public void setDescription(String description) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
