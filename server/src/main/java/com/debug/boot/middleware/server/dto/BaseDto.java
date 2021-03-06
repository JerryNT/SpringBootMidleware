package com.debug.boot.middleware.server.dto;

import java.io.Serializable;

public class BaseDto implements Serializable {
    private Integer id;
    private String content;

    public BaseDto() {
    }

    public BaseDto(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
