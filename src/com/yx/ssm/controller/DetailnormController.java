package com.yx.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yx.ssm.service.service.DetailnormService;
import com.yx.ssm.vo.DetailnormInputVo;
import com.yx.ssm.vo.DetailnormOutputVo;

@Controller
public class DetailnormController {

	@Autowired
	private DetailnormService detailnormService;
	
	@RequestMapping("/detailnormInit")
	public ModelAndView init() {
		ModelAndView modelAndView = new ModelAndView();
		DetailnormOutputVo detailnormOutputVo = detailnormService.init();
		modelAndView.addObject("detailnormOutputVo", detailnormOutputVo);
		modelAndView.setViewName("/jsp/detailnorm");
		return modelAndView;
	}
	
	@RequestMapping("/detailnormSelect")
	public ModelAndView detailnormSelect(DetailnormInputVo detailnormInputVo) {
		ModelAndView modelAndView = new ModelAndView();
		DetailnormOutputVo detailnormOutputVo = detailnormService.detailnormSelect(detailnormInputVo);
		modelAndView.addObject("detailnormOutputVo", detailnormOutputVo);
		modelAndView.setViewName("/jsp/detailnorm");
		return modelAndView;
	}
	
	@RequestMapping("/detailnormInsert")
	public ModelAndView detailnormInsert(DetailnormInputVo detailnormInputVo) {
		ModelAndView modelAndView = new ModelAndView();
		DetailnormOutputVo detailnormOutputVo = detailnormService.detailnormInsert(detailnormInputVo);
		modelAndView.addObject("detailnormOutputVo", detailnormOutputVo);
		modelAndView.setViewName("/jsp/detailnorm");
		return modelAndView;
	}
	
	@RequestMapping("/detailnormUpdate")
	public ModelAndView detailnormUpdate(DetailnormInputVo detailnormInputVo) {
		ModelAndView modelAndView = new ModelAndView();
		DetailnormOutputVo detailnormOutputVo = detailnormService.detailnormUpdate(detailnormInputVo);
		modelAndView.addObject("detailnormOutputVo", detailnormOutputVo);
		modelAndView.setViewName("/jsp/detailnorm");
		return modelAndView;
	}
	
	@RequestMapping("/detailnormFreeze")
	public ModelAndView detailnormFreeze(DetailnormInputVo detailnormInputVo) {
		ModelAndView modelAndView = new ModelAndView();
		DetailnormOutputVo detailnormOutputVo = detailnormService.detailnormFreeze(detailnormInputVo);
		modelAndView.addObject("detailnormOutputVo", detailnormOutputVo);
		modelAndView.setViewName("/jsp/detailnorm");
		return modelAndView;
	}
	
	@RequestMapping("/detailnormReport")
	public void detailnormReport(HttpServletRequest request, HttpServletResponse response, DetailnormInputVo detailnormInputVo) throws Exception {
		detailnormService.detailnormReport(request, response, detailnormInputVo);
	}
}
