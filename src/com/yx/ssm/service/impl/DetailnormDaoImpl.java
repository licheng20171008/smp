package com.yx.ssm.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.yx.ssm.po.DetailnormExt;
import com.yx.ssm.service.service.DetailnormDao;

public class DetailnormDaoImpl extends SqlSessionDaoSupport implements DetailnormDao {

	@Override
	public int insertSelective(List<DetailnormExt> detailnormExts) {
		SqlSession session = this.getSqlSession();
		int count = session.insert("com.yx.ssm.mapper.DetailnormExtMapper.insertSelective", detailnormExts);
		return count;
	}
}
