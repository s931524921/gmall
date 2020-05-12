package com.atguigu.gmall.sms.dao;

import com.atguigu.gmall.sms.entity.CouponSpuCategoryRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券分类关联
 * @author spl
 * @since  2020-05-12 15:07:18
 */
@Mapper
public interface CouponSpuCategoryRelationDao extends BaseMapper<CouponSpuCategoryRelation> {
	
}
