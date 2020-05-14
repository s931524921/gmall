package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.SkuInfo;
import com.atguigu.gmall.pms.entity.SkuSaleAttrValue;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuInfoVo extends SkuInfo{

    //积分营销相关字段
    private BigDecimal growBounds;
    private BigDecimal buyBounds;
    private List<Integer> work;

    //打折相关字段
    private Integer fullCount;
    private BigDecimal discount;
    private Integer ladderAddOther;

    //满减相关字段
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer fullAddOther;

    //销售属性及值
    private List<SkuSaleAttrValue> saleAttrs;

    //sku图片
    private List<String> images;

}
