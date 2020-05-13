package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gmall.pms.dao.AttrDao;
import com.atguigu.gmall.pms.dao.AttrGroupDao;
import com.atguigu.gmall.pms.entity.Attr;
import com.atguigu.gmall.pms.entity.AttrAttrgroupRelation;
import com.atguigu.gmall.pms.entity.AttrGroup;
import com.atguigu.gmall.pms.service.AttrGroupService;
import com.atguigu.gmall.pms.vo.GroupVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.core.bean.PageVo;
import com.java.core.bean.Query;
import com.java.core.bean.QueryCondition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroup> implements AttrGroupService {

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Autowired
    private AttrDao attrDao;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrGroup> page = this.page(
                new Query<AttrGroup>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo queryGroupByPage(QueryCondition condition, Long catId) {

        QueryWrapper<Attr> queryWrapper = new QueryWrapper();
        if (catId != null) {
            queryWrapper.eq("catalog_id", catId);
        }

        IPage<AttrGroup> page = this.page(
                new Query<AttrGroup>().getPage(condition),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    public GroupVO queryGroupWithAttrsByGid(long gid) {

        GroupVO groupVO = new GroupVO();
        //查询group
        AttrGroup group = this.getById(gid);
        BeanUtils.copyProperties(group, groupVO);

        //根据gid查询关联关系，并获取attrIds
        List<AttrAttrgroupRelation> relations = this.attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_group_id", gid));
        if (CollectionUtils.isEmpty(relations)) {
            return groupVO;
        }
        groupVO.setRelations(relations);

        //根据attrIds,所有的规格参数
        List<Long> attrIds = relations.stream().map(relationEntity -> relationEntity.getAttrGroupId()).collect(Collectors.toList());
        List<Attr> attrs = this.attrDao.selectBatchIds(attrIds);
        groupVO.setAttrEntities(attrs);

        return groupVO;
    }

}