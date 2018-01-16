package com.yx.ssm.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yx.ssm.mapper.DepartmentMapper;
import com.yx.ssm.mapper.DetailnormMapper;
import com.yx.ssm.po.Department;
import com.yx.ssm.po.DepartmentExample;
import com.yx.ssm.po.Detailnorm;
import com.yx.ssm.po.DetailnormExample;
import com.yx.ssm.po.Page;
import com.yx.ssm.service.action.Common;
import com.yx.ssm.service.action.Constant;
import com.yx.ssm.service.service.DepartmentService;
import com.yx.ssm.vo.CommonInputVo;
import com.yx.ssm.vo.CommonOutputVo;
import com.yx.ssm.vo.DepartmentInputVo;
import com.yx.ssm.vo.DepartmentOutputVo;

public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private DetailnormMapper detailnormMapper;
	
	@Override
	public DepartmentOutputVo init() {
		DepartmentOutputVo departmentOutputVo = new DepartmentOutputVo();
		
		// 显示列表初始化
		departmentOutputVo.setDepartments(null);
				
		// 项目初始化
		departmentOutputVo.setDepartment((Department) Common.InitBean(Department.class));
		CommonOutputVo commonOutputVo = new CommonOutputVo();
				
		// 信息初始化
		commonOutputVo.setMessage(Constant.EMPTY);
		
		// 查询项目
		commonOutputVo.setSelectBox(Constant.ONE);
		
		// 删除项目
		commonOutputVo.setDeleteItem(Constant.ZERO);
		
		// 分页项目
		commonOutputVo.setPage((Page) Common.InitBean(Page.class));
		departmentOutputVo.setCommonOutputVo(commonOutputVo);
		
		return departmentOutputVo;
	}

	@Override
	public DepartmentOutputVo departmentSelect(DepartmentInputVo departmentInputVo) {
		DepartmentOutputVo departmentOutputVo = new DepartmentOutputVo();
		this.initItem(departmentOutputVo, departmentInputVo);
		this.departmentPage(departmentOutputVo, departmentInputVo);
		return departmentOutputVo;
	}
	
	@Override
	public DepartmentOutputVo departmentInsert(DepartmentInputVo departmentInputVo) {
		DepartmentOutputVo departmentOutputVo = new DepartmentOutputVo();
		this.initItem(departmentOutputVo, departmentInputVo);
		departmentInputVo.getCommonInputVo().setSelectBox(Constant.ZERO);
		Department department = departmentInputVo.getDepartment();
		department.setCreatetime(new Date());
		int count = departmentMapper.insertSelective(department);
		if (count == 0) {
			departmentOutputVo.getCommonOutputVo().setErrorMessage("插入数据不成功，新增部门失败！！");
		} else {
			departmentOutputVo.getCommonOutputVo().setMessage("新增部门成功！！");
		}
		this.departmentPage(departmentOutputVo, departmentInputVo);
		return departmentOutputVo;
	}
	
	@Override
	public DepartmentOutputVo departmentUpdate(DepartmentInputVo departmentInputVo) {
		DepartmentOutputVo departmentOutputVo = new DepartmentOutputVo();
		this.initItem(departmentOutputVo, departmentInputVo);
		departmentInputVo.getCommonInputVo().setSelectBox(Constant.ZERO);
		Department department = departmentMapper.selectByPrimaryKey(departmentInputVo.getDepartment().getId());
		if (departmentInputVo.getDepartment().getDpname().equals(department.getDpname())) {
			departmentOutputVo.getCommonOutputVo().setWarMessage("部门信息无变化，无需修改部门信息！！");
		} else {
			if (StringUtils.isEmpty(department.getAbatetime())) {
				int count = departmentMapper.updateByPrimaryKeySelective(departmentInputVo.getDepartment());
				if (count == 0) {
					departmentOutputVo.getCommonOutputVo().setErrorMessage("更新数据不成功，修改部门失败！！");
				} else {
					departmentOutputVo.getCommonOutputVo().setMessage("修改部门成功！！");
				}
			} else {
				departmentOutputVo.getCommonOutputVo().setErrorMessage("冻结的部门信息无法进行修改！！");
			}
		}
		this.departmentPage(departmentOutputVo, departmentInputVo);
		return departmentOutputVo;
	}
	
	@Override
	public DepartmentOutputVo departmentFreeze(DepartmentInputVo departmentInputVo) {
		DepartmentOutputVo departmentOutputVo = new DepartmentOutputVo();
		this.initItem(departmentOutputVo, departmentInputVo);
		String deleteItem = departmentInputVo.getCommonInputVo().getDeleteItem();
		if (Constant.ONE.equals(deleteItem)) {
			DetailnormExample example = new DetailnormExample();
			DetailnormExample.Criteria criteria = example.createCriteria();
			criteria.andDepartmentidEqualTo(departmentInputVo.getDepartment().getId());
			List<Detailnorm> detailnorms = detailnormMapper.selectByExample(example);
			if (detailnorms == null || detailnorms.size() == 0) {
				int count = departmentMapper.deleteByPrimaryKey(departmentInputVo.getDepartment().getId());
				if (count == 0) {
					departmentOutputVo.getCommonOutputVo().setErrorMessage("部门不存在，删除部门失败！！");
				} else {
					departmentOutputVo.getCommonOutputVo().setMessage("删除部门成功！！");
					departmentOutputVo.setDepartment((Department) Common.InitBean(Department.class));
				}
			} else {
				departmentOutputVo.getCommonOutputVo().setWarMessage("部门在指标中已经被使用，无法删除，有必要请选择冻结！！");
			}
		} else {
			Department department = departmentMapper.selectByPrimaryKey(departmentInputVo.getDepartment().getId());
			if (!StringUtils.isEmpty(department)) {
				Date abatetime = department.getAbatetime();
				if (abatetime == null) {
					department.setAbatetime(new Date());
				} else {
					department.setAbatetime(null);
				}
				int count = departmentMapper.updateByPrimaryKey(department);
				if (count == 0) {
					departmentOutputVo.getCommonOutputVo().setErrorMessage("部门不存在，冻结/解冻部门失败！！");
				} else {
					departmentOutputVo.getCommonOutputVo().setMessage("冻结/解冻部门成功！！");
				}
			}else {
				departmentOutputVo.getCommonOutputVo().setErrorMessage("部门不存在，冻结/解冻部门失败！！");
			}
		}
		this.departmentPage(departmentOutputVo, departmentInputVo);
		return departmentOutputVo;
	}
	
	@Override
	public void departmentReport(HttpServletRequest request, HttpServletResponse response, DepartmentInputVo departmentInputVo) throws Exception {
		DepartmentOutputVo departmentOutputVo = new DepartmentOutputVo();
		CommonOutputVo commonOutputVo = new CommonOutputVo();
		commonOutputVo.setPageFlag(Constant.ONE);
		departmentOutputVo.setCommonOutputVo(commonOutputVo);
		this.departmentPage(departmentOutputVo, departmentInputVo);
		String fileTitle = "部门基本信息" + Common.sysDate("yyyy-MM-dd");
		String[][] itemArray = {{"部门ID", "部门名称", "冻结日期", "创建日期"}, {"id","dpname","abatetime","createtime"}};
		String servletPath = request.getSession().getServletContext().getRealPath("filePath") + File.separator + fileTitle + ".xls";
		Common.exportExcel(departmentOutputVo.getDepartments(), servletPath, itemArray, fileTitle);
		Common.download(servletPath, response);
		File file = new File(servletPath);
		if (file.exists()) {
			file.delete();
		}
	}
	
	@Override
	public List<Department> getDepartments(){
		DepartmentExample example = new DepartmentExample();
		DepartmentExample.Criteria criteria = example.createCriteria();
		criteria.andAbatetimeIsNull();
		example.setOrderByClause("id");
		List<Department> departments = departmentMapper.selectByExample(example);
		if (departments == null) {
			departments = new ArrayList<Department>();
		}
		return departments;
	};
	
	private void departmentPage(DepartmentOutputVo departmentOutputVo, DepartmentInputVo departmentInputVo) {
		DepartmentExample example = new DepartmentExample();
		List<Department> departments = null;
		Page page = new Page();
		String dpName = departmentInputVo.getDepartment().getDpname();
		DepartmentExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(dpName)) {
			if (Constant.ONE.equals(departmentInputVo.getCommonInputVo().getSelectBox())) {
				criteria.andDpnameLike("%" + dpName + "%");
			} else {
				criteria.andDpnameEqualTo(dpName);
			}
		}
		
		int count = departmentMapper.countByExample(example);
		if (count != 0) {
			page.setPerPage(Constant.PERPAGE);
			page.setCurPage(departmentInputVo.getCommonInputVo().getCurPage());
			if (count%Constant.PERPAGE == 0){
				page.setCountPage(count/Constant.PERPAGE);
			} else {
				page.setCountPage(count/Constant.PERPAGE + 1);
			}
			if (Constant.ONE.equals(departmentOutputVo.getCommonOutputVo().getPageFlag())) {
				example.setOrderByClause("id");
			} else {
				example.setOrderByClause(
						"id limit " + (page.getCurPage() -1)*Constant.PERPAGE + " , " + Constant.PERPAGE);
			}
			departments = departmentMapper.selectByExample(example);
		} else {
			page = (Page) Common.InitBean(Page.class);
			page.setPerPage(Constant.PERPAGE);
			departments = new ArrayList<Department>();
		}
		page.setCount(count);
		departmentOutputVo.setDepartments(departments);
		departmentOutputVo.getCommonOutputVo().setPage(page);
	}
	
	private void initItem(DepartmentOutputVo departmentOutputVo, DepartmentInputVo departmentInputVo) {
		CommonOutputVo commonOutputVo = new CommonOutputVo();
		CommonInputVo commonInputVo = departmentInputVo.getCommonInputVo();
		commonOutputVo.setDeleteItem(commonInputVo.getDeleteItem());
		commonOutputVo.setSelectBox(commonInputVo.getSelectBox());
		departmentOutputVo.setCommonOutputVo(commonOutputVo);
		departmentOutputVo.setDepartment(departmentInputVo.getDepartment());
	}
}