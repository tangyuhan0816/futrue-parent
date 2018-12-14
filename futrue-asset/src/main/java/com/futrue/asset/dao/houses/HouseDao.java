package com.futrue.asset.dao.houses;


import com.futrue.asset.bean.vo.HouseVo;
import com.futrue.common.page.PageableResponse;
import com.futrue.common.utils.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: HousesDao
 *  @package: com.futrue.asset.dao.houses
 *  @Date: Created in 2018/10/26 下午4:01
 *  @email yuhan.tang@magicwindow.cn
 *  @Description:
 */
@Component
public class HouseDao {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    public PageableResponse<HouseVo> near(String areaCode, BigDecimal longitudeX, BigDecimal latitudeY, Integer pageNumber, Integer pageSize){

        PageableResponse<HouseVo> response = new PageableResponse<>();

        Map<String ,Object> params = new HashMap<>(7);
        params.put("lonX",longitudeX);
        params.put("latY",latitudeY);
        params.put("district",areaCode);

        String sqlCount = "select count(1) from (SELECT (st_distance (point (longitude_x, latitude_y),point(:lonX,:latY) ) *111195) distance FROM  house s where s.district = :district and s.deleted = 0 HAVING distance < 200) m;";
        Long count = jdbcTemplate.queryForObject(sqlCount,params,Long.class);

        if(Preconditions.isNotBlank(count) && count > 0){
            if(pageNumber < 0){
                pageNumber = 0;
            }
            params.put("pageNumber", pageNumber * pageSize);
            params.put("pageSize",pageSize);
            String sqlPage = "SELECT  s.id,s.house_name, s.original_price, s.price, s.advice_num, s.banner_url, s.heat, (st_distance (point (longitude_x, latitude_y),point(:lonX,:latY) ) *111195 / 1000) AS distance  FROM  house s where s.district = :district and s.deleted = 0  HAVING distance < 30 ORDER BY distance limit :pageNumber, :pageSize;";
            List<HouseVo>  list = jdbcTemplate.query(sqlPage,params, new BeanPropertyRowMapper<>(HouseVo.class));
            for(HouseVo houseVo : list){
                houseVo.setDistance(houseVo.getDistance().setScale(1, BigDecimal.ROUND_HALF_DOWN));
            }
            response.setList(list);

        }
        response.setTotalCount(Preconditions.isBlank(count) ? 0 : count);
        response.setTotalPages(pageNumber);
        return response;
    }
}
