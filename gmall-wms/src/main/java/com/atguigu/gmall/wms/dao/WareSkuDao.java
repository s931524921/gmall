package com.atguigu.gmall.wms.dao;

import com.atguigu.gmall.wms.entity.WareSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * @author spl
 * @since  2020-05-13 16:40:34
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSku> {
	
}
