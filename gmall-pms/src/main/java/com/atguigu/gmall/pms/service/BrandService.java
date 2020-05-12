package com.atguigu.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.Brand;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;

/**
 * 品牌
 *
 * @author spl
 * @since  2020-05-12 11:49:27
 */
public interface BrandService extends IService<Brand> {

    PageVo queryPage(QueryCondition params);
}

