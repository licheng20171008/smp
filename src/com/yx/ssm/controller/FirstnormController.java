package com.yx.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yx.ssm.service.service.FirstnormService;
import com.yx.ssm.vo.FirstnormInputVo;
import com.yx.ssm.vo.FirstnormOutputVo;

@Controller
public class FirstnormController {

	@Autowired
	private FirstnormService firstnormService;
	
	@RequestMapping("/firstnormInit")
	public ModelAndView firstnormInit() {
		ModelAndView modelAndView = new ModelAndView();
		FirstnormOutputVo firstnormOutputVo = firstnormService.init();
		modelAndView.addObject("firstnormOutputVo", firstnormOutputVo);
		modelAndView.setViewName("/jsp/firstnorm");
		return modelAndView;
	}
	
	@RequestMapping("/firstnormSelect")
	public ModelAndView firstnormSelect(FirstnormInputVo firstnormInputVo) {
		ModelAndView modelAndView = new ModelAndView();
		FirstnormOutputVo firstnormOutputVo = firstnormService.firstnormSelect(firstnormInputVo);
		modelAndView.addObject("firstnormOutputVo", firstnormOutputVo);
		modelAndView.setViewName("/jsp/firstnorm");
		return modelAndView;
	}
	
	@RequestMapping("/firstnormInsert")
	public ModelAndView firstnormInsert(FirstnormInputVo firstnormInputVo) {
		ModelAndView modelAndView = new ModelAndView();
		FirstnormOutputVo firstnormOutputVo = firstnormService.firstnormInsert(firstnormInputVo);
		modelAndView.addObject("firstnormOutputVo", firstnormOutputVo);
		modelAndView.setViewName("/jsp/firstnorm");
		return modelAndView;
	}
	
	@RequestMapping("/firstnormUpdate")
	public ModelAndView firstnormUpdate(FirstnormInputVo firstnormInputVo) {
		ModelAndView modelAndView = new ModelAndView();
		FirstnormOutputVo firstnormOutputVo = firstnormService.firstnormUpdate(firstnormInputVo);
		modelAndView.addObject("firstnormOutputVo", firstnormOutputVo);
		modelAndView.setViewName("/jsp/firstnorm");
		return modelAndView;
	}
	
	@RequestMapping("/firstnormFreeze")
	public ModelAndView firstnormFreeze(FirstnormInputVo firstnormInputVo) {
		ModelAndView modelAndView = new ModelAndView();
		FirstnormOutputVo firstnormOutputVo = firstnormService.firstnormFreeze(firstnormInputVo);
		modelAndView.addObject("firstnormOutputVo", firstnormOutputVo);
		modelAndView.setViewName("/jsp/firstnorm");
		return modelAndView;
	}
	
	@RequestMapping("/firstnormReport")
	public void firstnormReport(HttpServletRequest request, HttpServletResponse response, FirstnormInputVo firstnormInputVo) throws Exception {
		firstnormService.firstnormReport(request, response, firstnormInputVo);
	}
}
