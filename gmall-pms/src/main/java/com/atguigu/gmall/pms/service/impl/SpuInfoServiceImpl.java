package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.dao.SpuInfoDao;
import com.atguigu.gmall.pms.entity.SpuInfo;
import com.atguigu.gmall.pms.service.SpuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.core.bean.PageVo;
import com.java.core.bean.Query;
import com.java.core.bean.QueryCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfo> implements SpuInfoService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<SpuInfo> page = this.page(
                new Query<SpuInfo>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo querySpuPage(QueryCondition queryCondition, Long cid) {

        QueryWrapper<SpuInfo> objectQueryWrapper = new QueryWrapper<>();

        //判断分类是否为0
        if (cid != 0) {
            objectQueryWrapper.eq("catalog_id", cid);
        }

        //判断关键字是否为空
        String key = queryCondition.getKey();
        if (StringUtils.isNoneBlank(key)) {
            objectQueryWrapper.and(t -> t.eq("id", key).or().like("spu_name", key));
        }

        IPage<SpuInfo> page = this.page(
                new Query<SpuInfo>().getPage(queryCondition),
                objectQueryWrapper
        );

        return new PageVo(page);
    }

}