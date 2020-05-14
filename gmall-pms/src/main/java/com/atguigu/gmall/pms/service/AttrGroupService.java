package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.vo.GroupVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.AttrGroup;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;

import java.util.List;

/**
 * 属性分组
 *
 * @author spl
 * @since  2020-05-12 11:49:27
 */
public interface AttrGroupService extends IService<AttrGroup> {

    PageVo queryPage(QueryCondition params);

    PageVo queryGroupByPage(QueryCondition condition, Long catId);

    GroupVO queryGroupWithAttrsByGid(long gid);

    List<GroupVO> queryGroupWithAttrsByCId(long cid);
}

