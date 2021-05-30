package org.example.exeptions;

public enum ResultEnum {
    USER_IS_ALREADY_EXIST (10,"User with such email is already exist"),
    USER_NOT_FOUND (11,"User with such email doesn't exist"),

    PRODUCT_IS_ALREADY_EXIST (20,"Product with such id is already exist"),
    PRODUCT_NOT_FOUND (21,"Product with such id not found");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
