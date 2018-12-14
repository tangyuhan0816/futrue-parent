package com.futrue.asset.repository.sys;

import com.futrue.common.base.BaseEntityRepository;
import com.futrue.common.entity.sys.ExtendInfo;

import java.util.List;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: ExtendInfoRepository
 *  @package: com.futrue.asset.dao.sys
 *  @Date: Created in 2018/10/30 下午3:30
 *  @email yuhan.tang@magicwindow.cn
 *  @Description:
 */
public interface ExtendInfoRepository extends BaseEntityRepository<ExtendInfo>{

    /**
     * 获取代理商banner图片
     * @param userId
     * @return
     */
    List<ExtendInfo> findByUserIdAndDeletedIsFalse(Long userId);
}
