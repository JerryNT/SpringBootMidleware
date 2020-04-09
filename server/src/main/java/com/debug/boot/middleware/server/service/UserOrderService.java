package com.debug.boot.middleware.server.service;

import com.debug.boot.middleware.model.dto.UserOrderPageDto;
import com.debug.boot.middleware.model.entity.UserOrder;
import com.debug.boot.middleware.model.mapper.UserOrderMapper;
import com.debug.boot.middleware.server.dto.UserOrderDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户订单service
 */
@Service
public class UserOrderService {

    private static final Logger log = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private UserOrderMapper userOrderMapper;

    public UserOrder getInfo(final String orderNo) throws Exception{
        if (StringUtils.isNotBlank(orderNo)) {
            return userOrderMapper.selectByOrderNo(orderNo);
        }
        return null;
    }

    public PageInfo<UserOrder> pageGetOrders(UserOrderPageDto dto) throws Exception{
        PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
        List<UserOrder> list = userOrderMapper.pageSelectOrder(dto);
        return new PageInfo<>(list);
    }


    //删除订单
    public Integer delete(final Integer id) throws Exception{
        //物理删除
        userOrderMapper.deleteByPrimaryKey(id);
        //逻辑删除
        //userOrderMapper.deleteOrder(id);
        return 1;
    }

    public Integer add(UserOrderDto dto) throws Exception{
        UserOrder entity = new UserOrder();
        BeanUtils.copyProperties(dto, entity);
        entity.setId(null);
        userOrderMapper.insertSelective(entity);
        return entity.getId();
    }

    public Integer update(UserOrderDto dto) throws Exception {
        UserOrder entity = userOrderMapper.selectByPrimaryKey(dto.getId());
        if (entity != null) {
            BeanUtils.copyProperties(dto, entity, "id");
            entity.setUpdateTime(DateTime.now().toDate());
            userOrderMapper.updateByPrimaryKeySelective(entity);
        }
        return 1;
    }
}
