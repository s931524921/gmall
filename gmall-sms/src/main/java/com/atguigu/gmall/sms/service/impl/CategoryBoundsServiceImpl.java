package com.atguigu.gmall.sms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.core.bean.PageVo;
import com.java.core.bean.Query;
import com.java.core.bean.QueryCondition;

import com.atguigu.gmall.sms.dao.CategoryBoundsDao;
import com.atguigu.gmall.sms.entity.CategoryBounds;
import com.atguigu.gmall.sms.service.CategoryBoundsService;

@Service("categoryBoundsService")
public class CategoryBoundsServiceImpl extends ServiceImpl<CategoryBoundsDao, CategoryBounds> implements CategoryBoundsService {

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<CategoryBounds> page = this.page(
                new Query<CategoryBounds>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

}