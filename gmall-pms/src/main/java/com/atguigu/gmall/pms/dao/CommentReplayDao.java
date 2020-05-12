package com.atguigu.gmall.pms.dao;

import com.atguigu.gmall.pms.entity.CommentReplay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * @author spl
 * @since  2020-05-12 11:49:27
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplay> {
	
}
