package com.futrue.common.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: BaseEntityRepository
 *  @package: com.futrue.common.base
 *  @Date: Created in 2018/7/20 下午5:57
 *  @email yuhan.tang@magicwindow.cn
 *  @Description: 
 */    
@NoRepositoryBean
public interface BaseEntityRepository <T extends BaseEntity> extends JpaRepository<T, Long> {

}
