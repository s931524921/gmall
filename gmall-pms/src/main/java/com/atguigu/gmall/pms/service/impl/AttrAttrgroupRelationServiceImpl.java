package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gmall.pms.entity.AttrAttrgroupRelation;
import com.atguigu.gmall.pms.service.AttrAttrgroupRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.core.bean.PageVo;
import com.java.core.bean.Query;
import com.java.core.bean.QueryCondition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelation> implements AttrAttrgroupRelationService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrAttrgroupRelation> page = this.page(
                new Query<AttrAttrgroupRelation>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    public void deleteAttrRelation(List<AttrAttrgroupRelation> attrgroupRelationList) {
        attrgroupRelationList.forEach(attrAttrgroupRelation -> {
            this.remove(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attrAttrgroupRelation.getAttrId()).eq("attr_group_id", attrAttrgroupRelation.getAttrGroupId()));
        });
    }

}