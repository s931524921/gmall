package com.atguigu.gmall.wms.controller;

import java.util.Arrays;

import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;
import com.java.core.bean.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gmall.wms.entity.WareOrderTaskDetail;
import com.atguigu.gmall.wms.service.WareOrderTaskDetailService;

/**
 * 库存工作单
 * @author spl
 * @since  2020-05-13 16:40:34
 */
@Api(tags = "库存工作单 管理")
@RestController
@RequestMapping("wms/wareordertaskdetail")
public class WareOrderTaskDetailController {
    @Autowired
    private WareOrderTaskDetailService wareOrderTaskDetailService;

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('wms:wareordertaskdetail:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = wareOrderTaskDetailService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('wms:wareordertaskdetail:info')")
    public Resp<WareOrderTaskDetail> info(@PathVariable("id") Long id){
		WareOrderTaskDetail wareOrderTaskDetail = wareOrderTaskDetailService.getById(id);

        return Resp.ok(wareOrderTaskDetail);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('wms:wareordertaskdetail:save')")
    public Resp<Object> save(@RequestBody WareOrderTaskDetail wareOrderTaskDetail){
		wareOrderTaskDetailService.save(wareOrderTaskDetail);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('wms:wareordertaskdetail:update')")
    public Resp<Object> update(@RequestBody WareOrderTaskDetail wareOrderTaskDetail){
		wareOrderTaskDetailService.updateById(wareOrderTaskDetail);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('wms:wareordertaskdetail:delete')")
    public Resp<Object> delete(@RequestBody Long[] ids){
		wareOrderTaskDetailService.removeByIds(Arrays.asList(ids));

        return Resp.ok(null);
    }

}
