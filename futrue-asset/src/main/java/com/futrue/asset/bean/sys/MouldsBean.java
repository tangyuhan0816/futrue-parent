package com.futrue.asset.bean.sys;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: MouldsBean
 *  @package: com.futrue.asset.bean.sys
 *  @Date: Created in 2018/10/31 下午5:02
 *  @email yuhan.tang@magicwindow.cn
 *  @Description: 
 */    
@Data
@ApiModel(value = "广告模版Bean")
public class MouldsBean {

    @ApiModelProperty("id")
    @JsonProperty("id")
    private Long id;

    @ApiModelProperty(value = "模版名称")
    @JSONField(name = "mould_name")
    @JsonProperty("mould_name")
    private String mouldName;

    @ApiModelProperty(value = "描述")
    @JSONField(name = "description")
    @JsonProperty("description")
    private String description;

    @ApiModelProperty(value = "视频url")
    @JSONField(name = "video_url")
    @JsonProperty("video_url")
    private String videoUrl;

    @ApiModelProperty(value = "背景url")
    @JSONField(name = "backgroud_url")
    @JsonProperty("backgroud_url")
    private String backgroudUrl;

    @ApiModelProperty(value = "url")
    @JSONField(name = "picture_url")
    @JsonProperty("picture_url")
    private String pictureUrl;

    @ApiModelProperty(value = "对应MouldCodeType枚举类")
    @JSONField(name = "mould_code")
    @JsonProperty("mould_code")
    private Integer mouldCode;

}
