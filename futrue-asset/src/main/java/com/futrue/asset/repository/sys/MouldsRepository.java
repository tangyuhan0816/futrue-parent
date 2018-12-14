package com.futrue.asset.repository.sys;

import com.futrue.common.base.BaseEntityRepository;
import com.futrue.common.entity.sys.Moulds;

import java.util.List;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: MouldsRepository
 *  @package: com.futrue.asset.service.sys
 *  @Date: Created in 2018/10/30 下午7:14
 *  @email yuhan.tang@magicwindow.cn
 *  @Description: 模版
 */    
public interface MouldsRepository extends BaseEntityRepository<Moulds>{

    Moulds findByIdAndDeletedIsFalse(Long id);

    Moulds findByIdAndUserIdAndDeletedIsFalse(Long id, Long userId);

    List<Moulds> findByUserIdAndDeletedIsFalse(Long userId);

}
