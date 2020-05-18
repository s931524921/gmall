package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.vo.SpuInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.core.bean.PageVo;
import com.java.core.bean.Query;
import com.java.core.bean.QueryCondition;

import com.atguigu.gmall.pms.dao.SpuInfoDescDao;
import com.atguigu.gmall.pms.entity.SpuInfoDesc;
import com.atguigu.gmall.pms.service.SpuInfoDescService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service("spuInfoDescService")
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescDao, SpuInfoDesc> implements SpuInfoDescService {

    @Transactional
    public void saveSpuInfoDesc(SpuInfoVo spuInfoVo, Long spuId) {
        List<String> spuImages = spuInfoVo.getSpuImages();
        if (CollectionUtils.isEmpty(spuImages)) {
            SpuInfoDesc spuInfoDesc = new SpuInfoDesc();
            spuInfoDesc.setSpuId(spuId);
            spuInfoDesc.setDecript(StringUtils.join(spuImages, ","));
            this.save(spuInfoDesc);
        }
    }

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<SpuInfoDesc> page = this.page(
                new Query<SpuInfoDesc>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

}