package com.yx.ssm.service.service;

import java.util.List;

import com.yx.ssm.po.Firstnorm;
import com.yx.ssm.po.FirstnormExample;

public interface FirstnormDao {

	public Firstnorm selectByPrimaryKey(Integer id);
	
	public List<Firstnorm> selectByExample(FirstnormExample example);
}
