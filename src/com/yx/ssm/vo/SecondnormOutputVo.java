package com.yx.ssm.vo;

import java.util.List;

import com.yx.ssm.po.Firstnorm;
import com.yx.ssm.po.Secondnorm;
import com.yx.ssm.po.SecondnormExt;

public class SecondnormOutputVo {

	private List<SecondnormExt> secondnormExts;
	
	private List<Firstnorm> firstnorms;
	
	private Secondnorm secondnorm;
	
	private CommonOutputVo commonOutputVo;

	public List<SecondnormExt> getSecondnormExts() {
		return secondnormExts;
	}

	public void setSecondnormExts(List<SecondnormExt> secondnormExts) {
		this.secondnormExts = secondnormExts;
	}

	public Secondnorm getSecondnorm() {
		return secondnorm;
	}

	public void setSecondnorm(Secondnorm secondnorm) {
		this.secondnorm = secondnorm;
	}

	public CommonOutputVo getCommonOutputVo() {
		return commonOutputVo;
	}

	public void setCommonOutputVo(CommonOutputVo commonOutputVo) {
		this.commonOutputVo = commonOutputVo;
	}

	public List<Firstnorm> getFirstnorms() {
		return firstnorms;
	}

	public void setFirstnorms(List<Firstnorm> firstnorms) {
		this.firstnorms = firstnorms;
	}
}
