package com.yx.ssm.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.yx.ssm.po.Department;
import com.yx.ssm.po.DepartmentExample;
import com.yx.ssm.service.service.DepartmentDao;

public class DepartmentDaoImpl extends SqlSessionDaoSupport implements DepartmentDao{

	@Override
	public Department selectByPrimaryKey(Integer id) {
		SqlSession session = this.getSqlSession();
		Department department = session.selectOne("com.yx.ssm.mapper.DepartmentMapper.selectByPrimaryKey", id);
		return department;
	}

	@Override
	public List<Department> selectByExample(DepartmentExample example) {
		SqlSession session = this.getSqlSession();
		List<Department> departments = session.selectList("com.yx.ssm.mapper.DepartmentMapper.selectByExample", example);
		return departments;
	}
}
