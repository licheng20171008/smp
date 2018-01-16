package com.yx.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yx.ssm.service.service.SecondnormService;
import com.yx.ssm.vo.SecondnormInputVo;
import com.yx.ssm.vo.SecondnormOutputVo;

@Controller
public class SecondnormController {

	@Autowired
	private SecondnormService secondnormService;
	
	@RequestMapping("/secondnormInit")
	public ModelAndView init() {
		ModelAndView modelAndView = new ModelAndView();
		SecondnormOutputVo secondnormOutputVo = secondnormService.init();
		modelAndView.addObject("secondnormOutputVo", secondnormOutputVo);
		modelAndView.setViewName("/jsp/secondnorm");
		return modelAndView;
	}
	
	@RequestMapping("/secondnormSelect")
	public ModelAndView secondnormSelect(SecondnormInputVo secondnormInputVo) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		SecondnormOutputVo secondnormOutputVo = secondnormService.secondnormSelect(secondnormInputVo);
		modelAndView.addObject("secondnormOutputVo", secondnormOutputVo);
		modelAndView.setViewName("/jsp/secondnorm");
		return modelAndView;
	}
	
	@RequestMapping("/secondnormInsert")
	public ModelAndView secondnormInsert(SecondnormInputVo secondnormInputVo) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		SecondnormOutputVo secondnormOutputVo = secondnormService.secondnormInsert(secondnormInputVo);
		modelAndView.addObject("secondnormOutputVo", secondnormOutputVo);
		modelAndView.setViewName("/jsp/secondnorm");
		return modelAndView;
	}
	
	@RequestMapping("/secondnormUpdate")
	public ModelAndView secondnormUpdate(SecondnormInputVo secondnormInputVo) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		SecondnormOutputVo secondnormOutputVo = secondnormService.secondnormUpdate(secondnormInputVo);
		modelAndView.addObject("secondnormOutputVo", secondnormOutputVo);
		modelAndView.setViewName("/jsp/secondnorm");
		return modelAndView;
	}
	
	@RequestMapping("/secondnormFreeze")
	public ModelAndView secondnormFreeze(SecondnormInputVo secondnormInputVo) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		SecondnormOutputVo secondnormOutputVo = secondnormService.secondnormFreeze(secondnormInputVo);
		modelAndView.addObject("secondnormOutputVo", secondnormOutputVo);
		modelAndView.setViewName("/jsp/secondnorm");
		return modelAndView;
	}
	
	@RequestMapping("/secondnormReport")
	public void secondnormReport(HttpServletRequest request, HttpServletResponse response, SecondnormInputVo secondnormInputVo) throws Exception {
		secondnormService.secondnormReport(request, response, secondnormInputVo);
	}
}
