package com.atguigu.gmall.pms.dao;

import com.atguigu.gmall.pms.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * @author spl
 * @since  2020-05-12 11:49:27
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {
	
}
