package com.futrue.asset.service.house;

import com.futrue.asset.repository.sys.ExtendInfoRepository;
import com.futrue.asset.service.sys.SysUserService;
import com.futrue.asset.bean.house.HouseBean;
import com.futrue.asset.bean.vo.HouseVo;
import com.futrue.asset.controller.sys.LoginController;
import com.futrue.asset.dao.houses.HouseDao;
import com.futrue.asset.repository.house.HouseRepository;
import com.futrue.asset.service.common.CommonService;
import com.futrue.common.entity.house.House;
import com.futrue.common.entity.sys.ExtendInfo;
import com.futrue.common.entity.sys.TbUser;
import com.futrue.common.exception.BusinessException;
import com.futrue.common.exception.HttpServiceException;
import com.futrue.common.exception.STException;
import com.futrue.common.page.PageableResponse;
import com.futrue.common.page.SortDirection;
import com.futrue.common.page.SortField;
import com.futrue.common.utils.PageableConverter;
import com.futrue.common.utils.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: HousesService
 *  @package: com.futrue.asset.service.house
 *  @Date: Created in 2018/10/26 上午11:57
 *  @email yuhan.tang@magicwindow.cn
 *  @Description:
 */
@Service
public class HouseService {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private HouseRepository housesRepository;

    @Autowired
    private HouseDao housesDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ExtendInfoRepository extendInfoRepository;

    public PageableResponse<HouseVo> page(HouseBean bean) throws HttpServiceException, InterruptedException {

        Page<House> page = null;

        if (Preconditions.isBlank(bean.getPageableRequest())) {
            throw new BusinessException("page is null");
        }

        String areaCode = null;

        if(Preconditions.isNotBlank(bean.getAreaCode())){
            areaCode = bean.getAreaCode();
        }

        if(Preconditions.isNotBlank(bean.getLatitudeY()) &&
                bean.getLatitudeY().compareTo(BigDecimal.ZERO) > 0 &&
                Preconditions.isNotBlank(bean.getLongitudeX()) &&
                bean.getLongitudeX().compareTo(BigDecimal.ZERO) > 0 ){

            areaCode = commonService.positioning(bean.getLatitudeY().toString(),bean.getLongitudeX().toString());
        }

        PageableResponse<HouseVo> response = new PageableResponse<>();

        List<SortField> sortFields = new ArrayList<>();

        SortField sortField = new SortField();

        sortField.setDirection(SortDirection.DESC);

        sortFields.add(sortField);

        bean.getPageableRequest().setSortFields(sortFields);

        if (bean.getType() == 0) {

            sortField.setFieldName("heat");

            PageRequest pageRequest = PageableConverter.toPageRequest(bean.getPageableRequest());

            page = housesRepository.findByDistrictAndDeletedIsFalse(areaCode,pageRequest);

        } else if (bean.getType() == 1) {

            sortField.setFieldName("averagePrice");

            PageRequest pageRequest = PageableConverter.toPageRequest(bean.getPageableRequest());

            page = housesRepository.findByDistrictAndDeletedIsFalse(areaCode,pageRequest);

        } else if (bean.getType() == 2 &&
                Preconditions.isNotBlank(bean.getLatitudeY()) &&
                Preconditions.isNotBlank(bean.getLongitudeX())) {

            return housesDao.near(areaCode,bean.getLongitudeX(), bean.getLatitudeY(), bean.getPageableRequest().getPageNumber(), bean.getPageableRequest().getPageSize());

        } else {

            throw new STException("error");
        }

        List<HouseVo> list = new ArrayList<>();

        for (House houses : page.getContent()) {

            HouseVo houseVo = new HouseVo();

            houseVo.conver(houses);

            list.add(houseVo);
        }

        response.setTotalPages(page.getTotalPages());

        response.setTotalCount(page.getTotalElements());

        response.setList(list);

        return response;
    }

    public HouseVo findDetail(Long id){
        House house = housesRepository.findByIdAndDeletedIsFalse(id);
        if(Preconditions.isBlank(house)){
            logger.error("house not found =========> {}",id);
            throw new STException("house not found");
        }
        HouseVo houseVo = new HouseVo();
        houseVo.converDetail(house);
        return houseVo;
    }

    public List<String> findBannerUrl(String phone) {
        TbUser user = sysUserService.findByPhone(phone);
        if (Preconditions.isBlank(user)) {
            throw new STException("token error");
        }
        TbUser parentUser = sysUserService.findById(user.getParentId());
        if (Preconditions.isBlank(parentUser)) {
            throw new STException("代理商用户未找到");
        }
        List<ExtendInfo> list = extendInfoRepository.findByUserIdAndDeletedIsFalse(parentUser.getUserId());
        return list.stream().map(ExtendInfo::getAgentBannerUrl).collect(Collectors.toList());
    }

    public House findById(Long id){
        return housesRepository.findByIdAndDeletedIsFalse(id);
    }

    public Integer findAdviceNum(Long id){
        House house = findById(id);
        if(Preconditions.isNotBlank(house)){
            return house.getAdviceNum();
        }
        return 0;
    }

}
