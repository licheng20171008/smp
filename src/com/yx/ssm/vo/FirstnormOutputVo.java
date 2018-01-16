package com.yx.ssm.vo;

import java.util.List;

import com.yx.ssm.po.Firstnorm;

public class FirstnormOutputVo {

	private List<Firstnorm> firstnorms;
	
	private Firstnorm firstnorm;
	
	private CommonOutputVo commonOutputVo;

	public List<Firstnorm> getFirstnorms() {
		return firstnorms;
	}

	public void setFirstnorms(List<Firstnorm> firstnorms) {
		this.firstnorms = firstnorms;
	}

	public Firstnorm getFirstnorm() {
		return firstnorm;
	}

	public void setFirstnorm(Firstnorm firstnorm) {
		this.firstnorm = firstnorm;
	}

	public CommonOutputVo getCommonOutputVo() {
		return commonOutputVo;
	}

	public void setCommonOutputVo(CommonOutputVo commonOutputVo) {
		this.commonOutputVo = commonOutputVo;
	}
}
