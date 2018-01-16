package com.yx.ssm.service.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yx.ssm.po.Department;
import com.yx.ssm.vo.DepartmentInputVo;
import com.yx.ssm.vo.DepartmentOutputVo;

public interface DepartmentService {

	public DepartmentOutputVo init();
	
	public DepartmentOutputVo departmentSelect(DepartmentInputVo departmentInputVo);
	
	public DepartmentOutputVo departmentInsert(DepartmentInputVo departmentInputVo);
	
	public DepartmentOutputVo departmentUpdate(DepartmentInputVo departmentInputVo);
	
	public DepartmentOutputVo departmentFreeze(DepartmentInputVo departmentInputVo);
	
	public void departmentReport(HttpServletRequest request, HttpServletResponse response, DepartmentInputVo departmentInputVo) throws Exception;
	
	public List<Department> getDepartments();
}
