package com.yx.ssm.mapper;

import java.util.List;

import com.yx.ssm.po.SecondnormExample;
import com.yx.ssm.po.SecondnormExt;

public interface SecondnormExtMapper {

	List<SecondnormExt> selectByExample(SecondnormExample example);
}
