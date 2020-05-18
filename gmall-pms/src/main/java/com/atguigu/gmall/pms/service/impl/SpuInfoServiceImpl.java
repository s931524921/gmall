package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.dao.SkuInfoDao;
import com.atguigu.gmall.pms.dao.SpuInfoDao;
import com.atguigu.gmall.pms.dao.SpuInfoDescDao;
import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.pms.feign.GmallSmsClient;
import com.atguigu.gmall.pms.service.*;
import com.atguigu.gmall.pms.vo.BaseAttrVo;
import com.atguigu.gmall.pms.vo.SkuInfoVo;
import com.atguigu.gmall.pms.vo.SpuInfoVo;
import com.atguigu.gmall.sms.vo.SkuSaleVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.core.bean.PageVo;
import com.java.core.bean.Query;
import com.java.core.bean.QueryCondition;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfo> implements SpuInfoService {

    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private SkuInfoDao skuInfoDao;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private GmallSmsClient smsClient;
    @Autowired
    private SpuInfoDescService saveSpuInfoDesc;

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

    @Override
    @GlobalTransactional
    public void bigSave(SpuInfoVo spuInfoVo) {

        //1.保存spu相关3张表
        //保存pms_spu_info
        Long spuId = saveSpuInfo(spuInfoVo);

        //保存pms_spu_info_desc
        this.saveSpuInfoDesc.saveSpuInfoDesc(spuInfoVo, spuId);

        //保存pms_product_attr_value
        saveBaseAttrValue(spuInfoVo, spuId);

        //2.保存sku相关3张表
        saveSkuAndSale(spuInfoVo, spuId);

        int i=1/0;
    }

    private void saveSkuAndSale(SpuInfoVo spuInfoVo, Long spuId) {
        List<SkuInfoVo> skus = spuInfoVo.getSkus();
        if (CollectionUtils.isEmpty(skus)) {
            return;
        }

        skus.forEach(skuInfoVo -> {
            skuInfoVo.setSpuId(spuId);
            skuInfoVo.setSkuCode(UUID.randomUUID().toString());
            skuInfoVo.setBrandId(spuInfoVo.getBrandId());
            skuInfoVo.setCatalogId(spuInfoVo.getCatalogId());
            List<String> images = skuInfoVo.getImages();
            if (!CollectionUtils.isEmpty(images)) {
                skuInfoVo.setSkuDefaultImg(StringUtils.isNotBlank(skuInfoVo.getSkuDefaultImg()) ? skuInfoVo.getSkuDefaultImg() : images.get(0));
            }
            //保存pms_sku_info
            this.skuInfoDao.insert(skuInfoVo);
            Long skuId = skuInfoVo.getSkuId();

            //保存pms_sku_images
            if (!CollectionUtils.isEmpty(images)) {
                List<SkuImages> skuImagesList = images.stream().map(image -> {
                    SkuImages skuImages = new SkuImages();
                    skuImages.setImgUrl(image);
                    skuImages.setSkuId(skuId);
                    skuImages.setDefaultImg(StringUtils.equals(skuInfoVo.getSkuDefaultImg(), image) ? 1 : 0);
                    return skuImages;
                }).collect(Collectors.toList());
                this.skuImagesService.saveBatch(skuImagesList);
            }

            //保存pms_sale_attr_value
            List<SkuSaleAttrValue> saleAttrs = skuInfoVo.getSaleAttrs();
            if (!CollectionUtils.isEmpty(saleAttrs)) {
                saleAttrs.forEach(skuSaleAttrValue -> skuSaleAttrValue.setSkuId(skuId));
                this.skuSaleAttrValueService.saveBatch(saleAttrs);
            }

            //3.保存营销信息相关3张表
            //远程调用sms
            SkuSaleVo skuSaleVo = new SkuSaleVo();
            BeanUtils.copyProperties(skuInfoVo, skuSaleVo);
            skuSaleVo.setSkuId(skuId);

            this.smsClient.saveSale(skuSaleVo);

        });
    }

    private void saveBaseAttrValue(SpuInfoVo spuInfoVo, Long spuId) {
        List<BaseAttrVo> baseAttrs = spuInfoVo.getBaseAttrs();
        if (!CollectionUtils.isEmpty(baseAttrs)) {
            List<ProductAttrValue> productAttrValues = baseAttrs.stream().map(baseAttrVo -> {
                ProductAttrValue productAttrValue = baseAttrVo;
                productAttrValue.setSpuId(spuId);
                return productAttrValue;
            }).collect(Collectors.toList());
            this.productAttrValueService.saveBatch(productAttrValues);
        }
    }

    private Long saveSpuInfo(SpuInfoVo spuInfoVo) {
        spuInfoVo.setCreateTime(new Date());
        spuInfoVo.setUpdateTime(spuInfoVo.getCreateTime());
        this.save(spuInfoVo);
        return spuInfoVo.getId();
    }

}