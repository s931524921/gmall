package com.atguigu.gmall.sms.service.impl;

import com.atguigu.gmall.sms.dao.SkuBoundsDao;
import com.atguigu.gmall.sms.dao.SkuFullReductionDao;
import com.atguigu.gmall.sms.dao.SkuLadderDao;
import com.atguigu.gmall.sms.entity.SkuBounds;
import com.atguigu.gmall.sms.entity.SkuFullReduction;
import com.atguigu.gmall.sms.entity.SkuLadder;
import com.atguigu.gmall.sms.service.SkuBoundsService;
import com.atguigu.gmall.sms.vo.SkuSaleVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.core.bean.PageVo;
import com.java.core.bean.Query;
import com.java.core.bean.QueryCondition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("skuBoundsService")
public class SkuBoundsServiceImpl extends ServiceImpl<SkuBoundsDao, SkuBounds> implements SkuBoundsService {

    @Autowired
    private SkuLadderDao skuLadderDao;
    @Autowired
    private SkuFullReductionDao skuFullReductionDao;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<SkuBounds> page = this.page(
                new Query<SkuBounds>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    @Override
    @Transactional
    public void saveSale(SkuSaleVo skuSaleVo) {

        //保存sms_sku_bounds
        SkuBounds skuBounds = new SkuBounds();
        BeanUtils.copyProperties(skuSaleVo, skuBounds);
        skuBounds.setSkuId(skuSaleVo.getSkuId());
        skuBounds.setGrowBounds(skuSaleVo.getGrowBounds());
        skuBounds.setBuyBounds(skuSaleVo.getBuyBounds());
        skuBounds.setWork(skuSaleVo.getWork().get(3) * 1 + skuSaleVo.getWork().get(2) * 2 + skuSaleVo.getWork().get(1) * 4 + skuSaleVo.getWork().get(0) * 8);
        this.save(skuBounds);

        //保存sms_sku_ladder
        SkuLadder skuLadder = new SkuLadder();
        skuLadder.setSkuId(skuSaleVo.getSkuId());
        skuLadder.setFullCount(skuSaleVo.getFullCount());
        skuLadder.setDiscount(skuSaleVo.getDiscount());
        skuLadder.setAddOther(skuSaleVo.getLadderAddOther());
        this.skuLadderDao.insert(skuLadder);

        //保存sms_sku_full_reduction
        SkuFullReduction skuFullReduction = new SkuFullReduction();
        skuFullReduction.setSkuId(skuSaleVo.getSkuId());
        skuFullReduction.setFullPrice(skuSaleVo.getFullPrice());
        skuFullReduction.setReducePrice(skuSaleVo.getReducePrice());
        skuFullReduction.setAddOther(skuSaleVo.getLadderAddOther());
        this.skuFullReductionDao.insert(skuFullReduction);

    }

}