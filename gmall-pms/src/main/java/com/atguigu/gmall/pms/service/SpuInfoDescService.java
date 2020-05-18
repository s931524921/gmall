package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.vo.SpuInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.SpuInfoDesc;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;

/**
 * spu信息介绍
 *
 * @author spl
 * @since  2020-05-12 11:49:27
 */
public interface SpuInfoDescService extends IService<SpuInfoDesc> {

    PageVo queryPage(QueryCondition params);

    void saveSpuInfoDesc(SpuInfoVo spuInfoVo, Long spuId);
}

