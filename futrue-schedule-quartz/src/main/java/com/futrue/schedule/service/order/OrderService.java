package com.futrue.schedule.service.order;

import com.futrue.common.entity.order.Order;
import com.futrue.schedule.dao.business.order.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: OrderService
 *  @package: com.futrue.schedule.service.order
 *  @Date: Created in 2018/11/13 下午3:39
 *  @email yuhan.tang@magicwindow.cn
 *  @Description: 
 */    
@Service
public class OrderService {


    @Autowired
    private OrderDao orderDao;

    public Order findById(){
        return orderDao.findById(1L);
    }

    public List<Order> findByOrderStatus(){
        return orderDao.findByOrderStatus();
    }

    public Integer updateOrderStatus(List<Order> list){
        return orderDao.updateStatus(list);
    }
}
