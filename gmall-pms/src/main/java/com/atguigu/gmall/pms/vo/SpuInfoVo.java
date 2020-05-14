package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.SpuInfo;
import lombok.Data;

import java.util.List;

@Data
public class SpuInfoVo extends SpuInfo {

    private List<String> spuImages;

    private List<BaseAttrVo> baseAttrs;

    private List<SkuInfoVo> skus;
}
