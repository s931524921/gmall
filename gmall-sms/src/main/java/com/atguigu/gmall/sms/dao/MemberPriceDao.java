package com.atguigu.gmall.sms.dao;

import com.atguigu.gmall.sms.entity.MemberPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 * @author spl
 * @since  2020-05-12 15:07:18
 */
@Mapper
public interface MemberPriceDao extends BaseMapper<MemberPrice> {
	
}
