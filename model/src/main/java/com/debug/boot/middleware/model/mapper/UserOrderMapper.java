package com.debug.boot.middleware.model.mapper;

import com.debug.boot.middleware.model.dto.UserOrderPageDto;
import com.debug.boot.middleware.model.entity.UserOrder;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserOrder record);

    int insertSelective(UserOrder record);

    UserOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOrder record);

    int updateByPrimaryKey(UserOrder record);

    UserOrder selectByOrderNo(@Param("orderNo") String orderNo);

    int deleteOrder(@Param("id") Integer id);

    List<UserOrder> pageSelectOrder(UserOrderPageDto dto);

    List<UserOrder> selectUnPayOrders(@Param("payStatus") Integer payStatus);

    void updateUnPayOrder(@Param("id") Integer id, @Param("payStatus") Integer unPayStatus);
}