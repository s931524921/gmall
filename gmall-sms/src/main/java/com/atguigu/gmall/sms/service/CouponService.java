package com.atguigu.gmall.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.sms.entity.Coupon;
import com.java.core.bean.PageVo;
import com.java.core.bean.QueryCondition;

/**
 * 优惠券信息
 *
 * @author spl
 * @since  2020-05-12 15:07:18
 */
public interface CouponService extends IService<Coupon> {

    PageVo queryPage(QueryCondition params);
}

