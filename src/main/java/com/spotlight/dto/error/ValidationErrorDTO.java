package com.spotlight.dto.error;

import java.util.List;

/**
 * @author IliaNik on 23.03.2017.
 */
public class ValidationErrorDTO {

    private String code;
    private String message;
    private List<FieldErrorDTO> fieldErrors;

    public ValidationErrorDTO() {
    }

    public ValidationErrorDTO(String code, String message, List<FieldErrorDTO> fieldErrors) {
        this.code = code;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public void addFieldError(String code, String message, String field) {
        FieldErrorDTO error = new FieldErrorDTO(code, message, field);
        fieldErrors.add(error);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    @Override
    public String toString() {
        return "BreakdownError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", fieldErrors=" + fieldErrors +
                '}';
    }
}
