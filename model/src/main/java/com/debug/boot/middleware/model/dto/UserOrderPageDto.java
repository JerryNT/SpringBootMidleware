package com.debug.boot.middleware.model.dto;

import java.io.Serializable;

/**
 * 用户分页订单查询dto
 */
public class UserOrderPageDto extends PageDto implements Serializable {
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
