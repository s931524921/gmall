package com.atguigu.gmall.sms.dao;

import com.atguigu.gmall.sms.entity.SeckillPromotion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动
 * @author spl
 * @since  2020-05-12 15:07:18
 */
@Mapper
public interface SeckillPromotionDao extends BaseMapper<SeckillPromotion> {
	
}
