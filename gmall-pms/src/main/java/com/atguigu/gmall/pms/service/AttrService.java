package com.atguigu.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.Attr;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;

/**
 * 商品属性
 *
 * @author spl
 * @since  2020-05-12 11:49:27
 */
public interface AttrService extends IService<Attr> {

    PageVo queryPage(QueryCondition params);
}

