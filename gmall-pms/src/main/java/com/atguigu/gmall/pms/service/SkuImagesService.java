package com.atguigu.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.SkuImages;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;

/**
 * sku图片
 *
 * @author spl
 * @since  2020-05-12 11:49:27
 */
public interface SkuImagesService extends IService<SkuImages> {

    PageVo queryPage(QueryCondition params);
}

