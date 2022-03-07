package com.example.useremailservice.dto.core;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.lang.Nullable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> {

    private String title;

    private String exception;

    private String desc;

    private T payload;

    public ResponseData(@Nullable T payload) {
        this.payload = payload;
    }

    public ResponseData(String title, @Nullable T payload) {
        this.title = title;
        this.payload = payload;
    }

    public ResponseData(Class<T> exception, String desc) {
        this.exception = exception.getSimpleName();
        this.desc = desc;
    }
}
