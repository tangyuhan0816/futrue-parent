package com.futrue.asset.repository.sys;

import com.futrue.common.base.BaseEntityRepository;
import com.futrue.common.entity.sys.TbUser;
import com.futrue.common.type.sys.UserTypeEnum;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: TbUserRepository
 *  @package: com.futrue.asset.dao.sys
 *  @Date: Created in 2018/10/9 下午5:42
 *  @email yuhan.tang@magicwindow.cn
 *  @Description:
 */
public interface TbUserRepository extends BaseEntityRepository<TbUser>{


    TbUser findByPhoneNumAndDeletedIsFalse(String phoneNum);


    /**
     * 查询区域管理员
     * @param userTypeEnum
     * @param agentArea
     * @return
     */
    TbUser findByAgentAreaAndUserTypeAndDeletedIsFalse(String agentArea, UserTypeEnum userTypeEnum);

    TbUser findByUserIdAndDeletedIsFalse(Long userId);
}
