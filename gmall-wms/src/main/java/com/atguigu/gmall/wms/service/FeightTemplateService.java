package com.atguigu.gmall.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.wms.entity.FeightTemplate;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;

/**
 * 运费模板
 *
 * @author spl
 * @since  2020-05-13 16:40:34
 */
public interface FeightTemplateService extends IService<FeightTemplate> {

    PageVo queryPage(QueryCondition params);
}

