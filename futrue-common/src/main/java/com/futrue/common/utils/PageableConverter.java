package com.futrue.common.utils;

import com.futrue.common.page.PageableRequest;
import com.futrue.common.page.SortDirection;
import com.futrue.common.page.SortField;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: PageableConverter
 *  @package: com.futrue.common.utils
 *  @Date: Created in 2018/7/20 下午5:08
 *  @email yuhan.tang@magicwindow.cn
 *  @Description:
 */
public class PageableConverter {

    public static PageRequest toPageRequest(PageableRequest pageableRequest) {
        Sort sort = null;
        if (Preconditions.isNotBlank(pageableRequest.getSortFields())) {
            List<Sort.Order> orders = new ArrayList<>();
            for (SortField sf : pageableRequest.getSortFields()) {
                Sort.Direction direction = sf.getDirection() == SortDirection.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
                Sort.Order order = new Sort.Order(direction, sf.getFieldName());
                orders.add(order);
            }
            sort = Sort.by(orders);
        }
        return Preconditions.isNotBlank(sort)
                ? PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize(), sort)
                : PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize());
    }
}
