package com.atguigu.gmall.pms.controller;

import com.atguigu.gmall.pms.entity.Category;
import com.atguigu.gmall.pms.service.CategoryService;
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
 * 商品三级分类
 *
 * @author spl
 * @since 2020-05-12 11:49:27
 */
@Api(tags = "商品三级分类 管理")
@RestController
@RequestMapping("pms/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Resp<List<Category>> queryCategoriesByPidLevel(@RequestParam(value = "level", defaultValue = "0") Integer level,
                                                          @RequestParam(value = "parentCid", required = false) Long pid) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        //判断分类的级别是否为0
        if (level != 0) {
            queryWrapper.eq("cat_level", level);
        }
        //判断父节点id是否为null
        if (pid != null) {
            queryWrapper.eq("parent_cid", pid);
        }

        List<Category> categoryList = categoryService.list(queryWrapper);

        return Resp.ok(categoryList);
    }


    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:category:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = categoryService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{catId}")
    @PreAuthorize("hasAuthority('pms:category:info')")
    public Resp<Category> info(@PathVariable("catId") Long catId) {
        Category category = categoryService.getById(catId);

        return Resp.ok(category);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('pms:category:save')")
    public Resp<Object> save(@RequestBody Category category) {
        categoryService.save(category);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('pms:category:update')")
    public Resp<Object> update(@RequestBody Category category) {
        categoryService.updateById(category);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('pms:category:delete')")
    public Resp<Object> delete(@RequestBody Long[] catIds) {
        categoryService.removeByIds(Arrays.asList(catIds));

        return Resp.ok(null);
    }

}
