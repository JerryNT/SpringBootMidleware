package com.debug.boot.middleware.server.scheduler;

import com.debug.boot.middleware.model.entity.UserOrder;
import com.debug.boot.middleware.model.mapper.UserOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

/**
 * 定时任务调度
 */
@Component
public class CommonScheduler {
    private static final Logger log= LoggerFactory.getLogger(CommonScheduler.class);
    private static final String CRON ="0/50 * * * * ? ";
    private static final Long UnPayTime = 3600000L;
    private static final Integer UnPayStatus=1;

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Scheduled(cron = CRON)
    @Async("taskExecutor")
    public void manageUnPayOrders() {
        try{
            List<UserOrder> list=userOrderMapper.selectUnPayOrders(UnPayStatus);

            //TODO:java8 stream api写法
            if (list!=null && !list.isEmpty()){
                list.forEach(userOrder -> {
                    Long orderTime=userOrder.getCreateTime().getTime();
                    Long currTime=System.currentTimeMillis();

                    if (currTime - orderTime > UnPayTime){
                        log.info("----当前订单已经超过了支付时间，即将对其进行失效：{}",userOrder.getOrderNo());

                        userOrderMapper.updateUnPayOrder(userOrder.getId(),UnPayStatus);
                    }
                });

            }
        } catch(Exception e) {
            log.error("定时任务调度-处理未支付的订单-发生异常：", e.fillInStackTrace());
        }
    }

    @Scheduled(cron = CRON)
    @Async("taskExecutor")
    public void schedulerOne(){
        log.info("----当定时任务1 ");

        try {
            Thread.sleep(2000);
        }catch (Exception e){}
    }

    @Scheduled(cron = CRON)
    @Async("taskExecutor")
    public void schedulerTwo(){
        log.info("----当定时任务2 ");

        try {
            Thread.sleep(2000);
        }catch (Exception e){}
    }

    @Scheduled(cron = CRON)
    @Async("taskExecutor")
    public void schedulerThree(){
        log.info("----当定时任务3 ");

        try {
            Thread.sleep(2000);
        }catch (Exception e){}
    }






    //TODO:先去user表查找is_active=0的用户列表(代表未激活) & 再去对应的mail_encrypt表查找是否有对应email邮箱的记录,如果没有发送邮箱的记录
    //TODO:那么我们可以补充再去发送一次邮件 -> 同时要插入一条 mail_encrypt发送记录
}
