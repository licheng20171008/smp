package com.yx.ssm.service.service;

import java.util.List;

import com.yx.ssm.po.Department;
import com.yx.ssm.po.DepartmentExample;

public interface DepartmentDao {

	public Department selectByPrimaryKey(Integer id);
	
	public List<Department> selectByExample(DepartmentExample example);
}
