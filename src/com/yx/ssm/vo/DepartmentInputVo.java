package com.yx.ssm.vo;

import com.yx.ssm.po.Department;

public class DepartmentInputVo {

	private Department department;
	
	private CommonInputVo commonInputVo;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public CommonInputVo getCommonInputVo() {
		return commonInputVo;
	}

	public void setCommonInputVo(CommonInputVo commonInputVo) {
		this.commonInputVo = commonInputVo;
	}
}
