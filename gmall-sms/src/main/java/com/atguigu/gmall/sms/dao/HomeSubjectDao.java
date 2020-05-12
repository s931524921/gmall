package com.atguigu.gmall.sms.dao;

import com.atguigu.gmall.sms.entity.HomeSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 * @author spl
 * @since  2020-05-12 15:07:18
 */
@Mapper
public interface HomeSubjectDao extends BaseMapper<HomeSubject> {
	
}
