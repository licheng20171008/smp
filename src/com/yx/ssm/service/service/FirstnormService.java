package com.yx.ssm.service.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yx.ssm.po.Firstnorm;
import com.yx.ssm.vo.FirstnormInputVo;
import com.yx.ssm.vo.FirstnormOutputVo;

public interface FirstnormService {

public FirstnormOutputVo init();
	
	public FirstnormOutputVo firstnormSelect(FirstnormInputVo firstnormInputVo);
	
	public FirstnormOutputVo firstnormInsert(FirstnormInputVo firstnormInputVo);
	
	public FirstnormOutputVo firstnormUpdate(FirstnormInputVo firstnormInputVo);
	
	public FirstnormOutputVo firstnormFreeze(FirstnormInputVo firstnormInputVo);
	
	public void firstnormReport(HttpServletRequest request, HttpServletResponse response, FirstnormInputVo firstnormInputVo) throws Exception;
	
	public List<Firstnorm> getFirstnorms();
}
