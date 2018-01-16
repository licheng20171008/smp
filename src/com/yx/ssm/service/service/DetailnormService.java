package com.yx.ssm.service.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yx.ssm.vo.DetailnormInputVo;
import com.yx.ssm.vo.DetailnormOutputVo;

public interface DetailnormService {

	public DetailnormOutputVo init();
	
	public DetailnormOutputVo detailnormSelect(DetailnormInputVo detailnormInputVo);
	
	public DetailnormOutputVo detailnormInsert(DetailnormInputVo detailnormInputVo);
	
	public DetailnormOutputVo detailnormUpdate(DetailnormInputVo detailnormInputVo);
	
	public DetailnormOutputVo detailnormFreeze(DetailnormInputVo detailnormInputVo);
	
	public void detailnormReport(HttpServletRequest request, HttpServletResponse response, DetailnormInputVo detailnormInputVo) throws Exception;
}
