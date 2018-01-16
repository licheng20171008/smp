package com.yx.ssm.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.yx.ssm.po.Secondnorm;
import com.yx.ssm.po.SecondnormExt;
import com.yx.ssm.service.service.SecondnormDao;

public class SecondnormDaoImpl extends SqlSessionDaoSupport implements SecondnormDao {

	@Override
	public Secondnorm selectByPrimaryKey(Integer id) {
		SqlSession session = this.getSqlSession();
		Secondnorm secondnorm = session.selectOne("com.yx.ssm.mapper.SecondnormMapper.selectByPrimaryKey", id);
		return secondnorm;
	}

	@Override
	public List<Secondnorm> selectByExample(SecondnormExt secondnormExt) {
		SqlSession session = this.getSqlSession();
		List<Secondnorm> secondnorms = session.selectList("com.yx.ssm.mapper.SecondnormExtMapper.selectByName", secondnormExt);
		return secondnorms;
	}
}
