package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gmall.pms.dao.AttrDao;
import com.atguigu.gmall.pms.entity.Attr;
import com.atguigu.gmall.pms.entity.AttrAttrgroupRelation;
import com.atguigu.gmall.pms.service.AttrService;
import com.atguigu.gmall.pms.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.core.bean.PageVo;
import com.java.core.bean.Query;
import com.java.core.bean.QueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, Attr> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<Attr> page = this.page(
                new Query<Attr>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo queryAttrsByCid(QueryCondition condition, Long cid, Integer type) {

        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>();
        if (cid != null) {
            queryWrapper.eq("catalog_id", cid);
        }
        queryWrapper.eq("attr_type", type);

        IPage<Attr> page = this.page(
                new Query<Attr>().getPage(condition),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    public void saveAttr(AttrVo attrVo) {
        //新增attr
        this.save(attrVo);

        //新增中间表
        AttrAttrgroupRelation attrAttrgroupRelation = new AttrAttrgroupRelation();
        attrAttrgroupRelation.setAttrId(attrVo.getAttrId());
        attrAttrgroupRelation.setAttrGroupId(attrVo.getAttrGroupId());
        this.attrAttrgroupRelationDao.insert(attrAttrgroupRelation);

    }

}