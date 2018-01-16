package com.yx.ssm.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.yx.ssm.po.Firstnorm;
import com.yx.ssm.po.FirstnormExample;
import com.yx.ssm.service.service.FirstnormDao;

public class FirstnormDaoImpl extends SqlSessionDaoSupport implements FirstnormDao {

	@Override
	public Firstnorm selectByPrimaryKey(Integer id) {
		SqlSession session = this.getSqlSession();
		Firstnorm firstnorm = session.selectOne("com.yx.ssm.mapper.FirstnormMapper.selectByPrimaryKey", id);
		return firstnorm;
	}

	@Override
	public List<Firstnorm> selectByExample(FirstnormExample example) {
		SqlSession session = this.getSqlSession();
		List<Firstnorm> firstnorms = session.selectList("com.yx.ssm.mapper.FirstnormMapper.selectByExample", example);
		return firstnorms;
	}
}
