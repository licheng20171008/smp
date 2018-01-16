package com.yx.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yx.ssm.service.service.DepartmentService;
import com.yx.ssm.vo.DepartmentInputVo;
import com.yx.ssm.vo.DepartmentOutputVo;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("/departmentInit")
	public ModelAndView departmentInit() {
		ModelAndView modelAndView = new ModelAndView();
		DepartmentOutputVo departmentOutputVo = departmentService.init();
		modelAndView.addObject("departmentOutputVo", departmentOutputVo);
		modelAndView.setViewName("/jsp/department");
		return modelAndView;
	}
	
	@RequestMapping("/departmentSelect")
	public ModelAndView departmentSelect(DepartmentInputVo departmentInputVo) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		DepartmentOutputVo departmentOutputVo = departmentService.departmentSelect(departmentInputVo);
		modelAndView.addObject("departmentOutputVo", departmentOutputVo);
		modelAndView.setViewName("/jsp/department");
		return modelAndView;
	}
	
	@RequestMapping("/departmentInsert")
	public ModelAndView departmentInsert(DepartmentInputVo departmentInputVo) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		DepartmentOutputVo departmentOutputVo = departmentService.departmentInsert(departmentInputVo);
		modelAndView.addObject("departmentOutputVo", departmentOutputVo);
		modelAndView.setViewName("/jsp/department");
		return modelAndView;
	}
	
	@RequestMapping("/departmentUpdate")
	public ModelAndView departmentUpdate(DepartmentInputVo departmentInputVo) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		DepartmentOutputVo departmentOutputVo = departmentService.departmentUpdate(departmentInputVo);
		modelAndView.addObject("departmentOutputVo", departmentOutputVo);
		modelAndView.setViewName("/jsp/department");
		return modelAndView;
	}
	
	@RequestMapping("/departmentFreeze")
	public ModelAndView departmentFreeze(DepartmentInputVo departmentInputVo) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		DepartmentOutputVo departmentOutputVo = departmentService.departmentFreeze(departmentInputVo);
		modelAndView.addObject("departmentOutputVo", departmentOutputVo);
		modelAndView.setViewName("/jsp/department");
		return modelAndView;
	}
	
	@RequestMapping("/departmentReport")
	public void departmentReport(HttpServletRequest request, HttpServletResponse response, DepartmentInputVo departmentInputVo) throws Exception {
		departmentService.departmentReport(request, response, departmentInputVo);
	}
}
