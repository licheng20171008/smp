package com.yx.ssm.service.service;

import java.util.List;

import com.yx.ssm.po.DetailnormExt;

public interface DetailnormDao {

	public int insertSelective(List<DetailnormExt> detailnormExts);
}
