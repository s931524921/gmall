package com.atguigu.gmall.sms.dao;

import com.atguigu.gmall.sms.entity.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * @author spl
 * @since  2020-05-12 15:07:18
 */
@Mapper
public interface CouponDao extends BaseMapper<Coupon> {
	
}
