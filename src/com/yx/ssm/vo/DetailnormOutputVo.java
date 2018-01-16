package com.yx.ssm.vo;

import java.util.List;

import com.yx.ssm.po.Department;
import com.yx.ssm.po.Detailnorm;
import com.yx.ssm.po.DetailnormExt;
import com.yx.ssm.po.Firstnorm;
import com.yx.ssm.po.Secondnorm;

public class DetailnormOutputVo {

	private List<DetailnormExt> detailnormExts;
	
	private List<Firstnorm> firstnorms;
	
	private List<Secondnorm> secondnorms;
	
	private List<Department> departments;
	
	private Detailnorm detailnorm;
	
	private CommonOutputVo commonOutputVo;

	public List<DetailnormExt> getDetailnormExts() {
		return detailnormExts;
	}

	public void setDetailnormExts(List<DetailnormExt> detailnormExts) {
		this.detailnormExts = detailnormExts;
	}

	public Detailnorm getDetailnorm() {
		return detailnorm;
	}

	public void setDetailnorm(Detailnorm detailnorm) {
		this.detailnorm = detailnorm;
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

	public List<Secondnorm> getSecondnorms() {
		return secondnorms;
	}

	public void setSecondnorms(List<Secondnorm> secondnorms) {
		this.secondnorms = secondnorms;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
}
