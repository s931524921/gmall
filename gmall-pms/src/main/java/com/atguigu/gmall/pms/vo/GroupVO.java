package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.Attr;
import com.atguigu.gmall.pms.entity.AttrAttrgroupRelation;
import com.atguigu.gmall.pms.entity.AttrGroup;
import lombok.Data;

import java.util.List;

@Data
public class GroupVO extends AttrGroup {

    private List<Attr> attrEntities;

    private List<AttrAttrgroupRelation> relations;

}
