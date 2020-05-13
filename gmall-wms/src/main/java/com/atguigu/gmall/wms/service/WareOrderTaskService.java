package com.atguigu.gmall.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.wms.entity.WareOrderTask;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;

/**
 * 库存工作单
 *
 * @author spl
 * @since  2020-05-13 16:40:34
 */
public interface WareOrderTaskService extends IService<WareOrderTask> {

    PageVo queryPage(QueryCondition params);
}

