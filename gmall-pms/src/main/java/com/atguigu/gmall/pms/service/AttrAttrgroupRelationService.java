package com.atguigu.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.AttrAttrgroupRelation;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author spl
 * @since  2020-05-12 11:49:27
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelation> {

    PageVo queryPage(QueryCondition params);

    void deleteAttrRelation(List<AttrAttrgroupRelation> attrgroupRelationList);

}

