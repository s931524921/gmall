package com.atguigu.gmall.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.wms.entity.WareSku;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;

/**
 * 商品库存
 *
 * @author spl
 * @since  2020-05-13 16:40:34
 */
public interface WareSkuService extends IService<WareSku> {

    PageVo queryPage(QueryCondition params);
}

