package com.futrue.common.entity.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.futrue.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: Order
 *  @package: com.futrue.common.entity.order
 *  @Date: Created in 2018/11/8 下午2:50
 *  @email yuhan.tang@magicwindow.cn
 *  @Description:
 */
@Data
@Entity
@Table(name = "`order`")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键")
    @JSONField(name = "id")
    @JsonProperty("id")
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value = "订单编码")
    @JSONField(name = "order_no")
    @JsonProperty("order_no")
    @Column(name = "order_no")
    private String orderNo;

    @ApiModelProperty(value = "商品总价")
    @JSONField(name = "product_amount_total")
    @JsonProperty("product_amount_total")
    @Column(name = "product_amount_total")
    private BigDecimal productAmountTotal;

    @ApiModelProperty(value = "实际付款金额")
    @JSONField(name = "order_amount_total")
    @JsonProperty("order_amount_total")
    @Column(name = "order_amount_total")
    private BigDecimal orderAmountTotal;

    @ApiModelProperty(value = "价格单位")
    @JSONField(name = "price_unit")
    @JsonProperty("price_unit")
    @Column(name = "price_unit")
    private String priceUnit;

    @ApiModelProperty(value = "支付单号")
    @JSONField(name = "payment_order_no")
    @JsonProperty("payment_order_no")
    @Column(name = "payment_order_no")
    private String paymentOrderNo;

    @ApiModelProperty(value = "详细地址")
    @JSONField(name = "street")
    @JsonProperty("street")
    @Column(name = "street")
    private String street;

    @ApiModelProperty(value = "收货人手机号")
    @JSONField(name = "phone_number")
    @JsonProperty("phone_number")
    @Column(name = "phone_number")
    private String phoneNumber;

    @ApiModelProperty(value = "收货人姓名")
    @JSONField(name = "name")
    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "订单属性")
    @JSONField(name = "order_type")
    @JsonProperty("order_type")
    @Column(name = "order_type")
    private Integer orderType;

    @ApiModelProperty(value = "支付渠道")
    @JSONField(name = "pay_channel")
    @JsonProperty("pay_channel")
    @Column(name = "pay_channel")
    private Integer payChannel;

    @ApiModelProperty(value = "支付时间")
    @JSONField(name = "pay_time")
    @JsonProperty("pay_time")
    @Column(name = "pay_time")
    private Date payTime;

    @ApiModelProperty(value = "投放模版ID")
    @JSONField(name = "mould_id")
    @JsonProperty("mould_id")
    @Column(name = "mould_id")
    private Long mouldId;

    @ApiModelProperty(value = "对应MouldCodeType枚举类")
    @JSONField(name = "mould_code")
    @JsonProperty("mould_code")
    @Column(name = "mould_code")
    private Integer mouldCode;

    @ApiModelProperty(value = "用户id")
    @JSONField(name = "user_id")
    @JsonProperty("user_id")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "商家id")
    @JSONField(name = "provider_id")
    @JsonProperty("provider_id")
    @Column(name = "provider_id")
    private Long providerId;

    @ApiModelProperty(value = "备注")
    @JSONField(name = "mark")
    @JsonProperty("mark")
    @Column(name = "mark")
    private String mark;

    @ApiModelProperty(value = "订单状态")
    @JSONField(name = "order_status")
    @JsonProperty("order_status")
    @Column(name = "order_status")
    private Integer orderStatus;

    @ApiModelProperty(value = "预支付订单返回支付配置订单")
    @JSONField(name = "payment_config_info")
    @JsonProperty("payment_config_info")
    @Column(name = "payment_config_info")
    private String paymentConfigInfo;
}
