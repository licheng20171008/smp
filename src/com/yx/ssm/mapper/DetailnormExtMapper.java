package com.yx.ssm.mapper;

import java.util.List;

import com.yx.ssm.po.DetailnormExample;
import com.yx.ssm.po.DetailnormExt;

public interface DetailnormExtMapper {

	List<DetailnormExt> selectByExample(DetailnormExample example);
}
