package com.yx.ssm.vo;

import java.util.List;

import com.yx.ssm.po.Department;

public class DepartmentOutputVo {

	private List<Department> departments;
	
	private Department department;
	
	private CommonOutputVo commonOutputVo;
	
	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public CommonOutputVo getCommonOutputVo() {
		return commonOutputVo;
	}

	public void setCommonOutputVo(CommonOutputVo commonOutputVo) {
		this.commonOutputVo = commonOutputVo;
	}
}
