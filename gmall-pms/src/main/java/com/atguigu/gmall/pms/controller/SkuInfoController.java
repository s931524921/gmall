package com.atguigu.gmall.pms.controller;

import com.atguigu.gmall.pms.entity.SkuInfo;
import com.atguigu.gmall.pms.service.SkuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;
import com.java.core.bean.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * sku信息
 *
 * @author spl
 * @since 2020-05-12 11:49:27
 */
@Api(tags = "sku信息 管理")
@RestController
@RequestMapping("pms/skuinfo")
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;

    @GetMapping("{spuId}")
    public Resp<List<SkuInfo>> querySkusBySpuId(@PathVariable("spuId") Long spuId) {

        List<SkuInfo> skuInfoList = this.skuInfoService.list(new QueryWrapper<SkuInfo>().eq("spu_id", spuId));
        return Resp.ok(skuInfoList);
    }

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:skuinfo:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = skuInfoService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{skuId}")
    @PreAuthorize("hasAuthority('pms:skuinfo:info')")
    public Resp<SkuInfo> info(@PathVariable("skuId") Long skuId) {
        SkuInfo skuInfo = skuInfoService.getById(skuId);

        return Resp.ok(skuInfo);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('pms:skuinfo:save')")
    public Resp<Object> save(@RequestBody SkuInfo skuInfo) {
        skuInfoService.save(skuInfo);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('pms:skuinfo:update')")
    public Resp<Object> update(@RequestBody SkuInfo skuInfo) {
        skuInfoService.updateById(skuInfo);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('pms:skuinfo:delete')")
    public Resp<Object> delete(@RequestBody Long[] skuIds) {
        skuInfoService.removeByIds(Arrays.asList(skuIds));

        return Resp.ok(null);
    }

}
