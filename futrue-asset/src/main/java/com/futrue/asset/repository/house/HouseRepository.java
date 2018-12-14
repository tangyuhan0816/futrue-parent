package com.futrue.asset.repository.house;

import com.futrue.common.base.BaseEntityRepository;
import com.futrue.common.entity.house.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: HouseRepository
 *  @package: com.futrue.asset.dao.house
 *  @Date: Created in 2018/10/26 上午11:55
 *  @email yuhan.tang@magicwindow.cn
 *  @Description:
 */    
public interface HouseRepository extends BaseEntityRepository<House> {

    /**
     * 获取当前人所在城市分页
     * @param areaCode
     * @param pageable
     * @return
     */
    Page<House> findByDistrictAndDeletedIsFalse(String areaCode,Pageable pageable);

    House findByIdAndDeletedIsFalse(Long id);
}
