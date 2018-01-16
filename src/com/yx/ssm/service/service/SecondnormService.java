package com.yx.ssm.service.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yx.ssm.po.Secondnorm;
import com.yx.ssm.vo.SecondnormInputVo;
import com.yx.ssm.vo.SecondnormOutputVo;

public interface SecondnormService {

	public SecondnormOutputVo init();
	
	public SecondnormOutputVo secondnormSelect(SecondnormInputVo secondnormInputVo);
	
	public SecondnormOutputVo secondnormInsert(SecondnormInputVo secondnormInputVo);
	
	public SecondnormOutputVo secondnormUpdate(SecondnormInputVo secondnormInputVo);
	
	public SecondnormOutputVo secondnormFreeze(SecondnormInputVo secondnormInputVo);
	
	public void secondnormReport(HttpServletRequest request, HttpServletResponse response, SecondnormInputVo secondnormInputVo) throws Exception;
	
	public List<Secondnorm> getSecondnorms(int firstnormid);
}
