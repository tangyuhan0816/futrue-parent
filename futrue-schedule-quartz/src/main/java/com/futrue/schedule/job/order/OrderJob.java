package com.futrue.schedule.job.order;

import com.futrue.common.entity.order.Order;
import com.futrue.common.utils.Preconditions;
import com.futrue.schedule.service.order.OrderService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: OrderJob
 *  @package: com.futrue.schedule.job.order
 *  @Date: Created in 2018/11/14 上午11:23
 *  @email yuhan.tang@magicwindow.cn
 *  @Description: Order 定时处理一小时未支付的订单
 */
public class OrderJob extends QuartzJobBean {

    private static Logger logger = LoggerFactory.getLogger(OrderJob.class);

    @Autowired
    private OrderService orderService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Order> orderList = orderService.findByOrderStatus();
        logger.info("======================== orderJob start 一小时未支付订单数量：{} ======================== ", orderList.size());
        Integer count = 0;
        if(orderList.size() > 0){

            int updatePage = orderList.size() / 100;
            if(Preconditions.isNotBlank(orderList.size()) && updatePage <= 1){
                //直接批量保存
                count = orderService.updateOrderStatus(orderList);
            } else if(updatePage > 1 && updatePage < 10){

                //分批保存 100条数据一次
                int pageNum = orderList.size() % 100;

                List<Order> updateList = new ArrayList<>();

                for(int i = 0;i < updatePage;i++){
                    updateList = orderList.subList(i * 100, (i + 1) * 100);
                    count = orderService.updateOrderStatus(updateList);
                }

                if(pageNum > 0){
                    updateList = orderList.subList(updatePage * 10 * 10, updatePage * 10 * 10 + pageNum);
                    orderService.updateOrderStatus(updateList);
                }
            } else {
                //启用多线程协同处理大数据量 暂时不做
            }
        }
        logger.info("======================== orderJob end 一小时未支付订单数量：{}, 修改成功数量:{} ========================", orderList.size(), count);
    }
}
