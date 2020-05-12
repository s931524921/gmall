package com.atguigu.gmall.sms.dao;

import com.atguigu.gmall.sms.entity.SkuFullReduction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品满减信息
 * @author spl
 * @since  2020-05-12 15:07:17
 */
@Mapper
public interface SkuFullReductionDao extends BaseMapper<SkuFullReduction> {
	
}
