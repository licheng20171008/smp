package com.yx.ssm.service.service;

import java.util.List;

import com.yx.ssm.po.Secondnorm;
import com.yx.ssm.po.SecondnormExt;

public interface SecondnormDao {

	public Secondnorm selectByPrimaryKey(Integer id);
	
	public List<Secondnorm> selectByExample(SecondnormExt secondnormExt);
}
