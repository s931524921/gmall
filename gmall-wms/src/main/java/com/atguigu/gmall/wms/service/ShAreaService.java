package com.atguigu.gmall.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.wms.entity.ShArea;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;

/**
 * 全国省市区信息
 *
 * @author spl
 * @since  2020-05-13 16:40:34
 */
public interface ShAreaService extends IService<ShArea> {

    PageVo queryPage(QueryCondition params);
}

