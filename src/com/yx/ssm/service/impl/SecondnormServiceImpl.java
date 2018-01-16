package com.yx.ssm.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yx.ssm.mapper.DetailnormMapper;
import com.yx.ssm.mapper.SecondnormExtMapper;
import com.yx.ssm.mapper.SecondnormMapper;
import com.yx.ssm.po.Detailnorm;
import com.yx.ssm.po.DetailnormExample;
import com.yx.ssm.po.Page;
import com.yx.ssm.po.Secondnorm;
import com.yx.ssm.po.SecondnormExample;
import com.yx.ssm.po.SecondnormExt;
import com.yx.ssm.service.action.Common;
import com.yx.ssm.service.action.Constant;
import com.yx.ssm.service.service.FirstnormService;
import com.yx.ssm.service.service.SecondnormService;
import com.yx.ssm.vo.CommonInputVo;
import com.yx.ssm.vo.CommonOutputVo;
import com.yx.ssm.vo.SecondnormInputVo;
import com.yx.ssm.vo.SecondnormOutputVo;

public class SecondnormServiceImpl implements SecondnormService {

	@Autowired
	private FirstnormService firstnormService;
	
	@Autowired
	private SecondnormMapper secondnormMapper;
	
	@Autowired
	private SecondnormExtMapper secondnormExtMapper;
	
	@Autowired
	private DetailnormMapper detailnormMapper;
	
	@Override
	public SecondnormOutputVo init() {
		SecondnormOutputVo secondnormOutputVo = new SecondnormOutputVo();
		
		// 显示列表初始化
		secondnormOutputVo.setSecondnormExts(null);
				
		// 项目初始化
		secondnormOutputVo.setSecondnorm((Secondnorm) Common.InitBean(Secondnorm.class));
		
		// 一级分类选项
		secondnormOutputVo.setFirstnorms(firstnormService.getFirstnorms());
		CommonOutputVo commonOutputVo = new CommonOutputVo();
				
		// 信息初始化
		commonOutputVo.setMessage(Constant.EMPTY);
		
		// 查询项目
		commonOutputVo.setSelectBox(Constant.ONE);
		
		// 删除项目
		commonOutputVo.setDeleteItem(Constant.ZERO);
		
		// 分页项目
		commonOutputVo.setPage((Page) Common.InitBean(Page.class));
		secondnormOutputVo.setCommonOutputVo(commonOutputVo);
		
		return secondnormOutputVo;
	}

	@Override
	public SecondnormOutputVo secondnormSelect(SecondnormInputVo secondnormInputVo) {
		SecondnormOutputVo secondnormOutputVo = new SecondnormOutputVo();
		this.initItem(secondnormOutputVo, secondnormInputVo);
		this.secondnormPage(secondnormOutputVo, secondnormInputVo);
		return secondnormOutputVo;
	}

	@Override
	public SecondnormOutputVo secondnormInsert(SecondnormInputVo secondnormInputVo) {
		SecondnormOutputVo secondnormOutputVo = new SecondnormOutputVo();
		this.initItem(secondnormOutputVo, secondnormInputVo);
		secondnormInputVo.getCommonInputVo().setSelectBox(Constant.ZERO);
		Secondnorm secondnorm = secondnormInputVo.getSecondnorm();
		secondnorm.setCreatetime(new Date());
		int count = secondnormMapper.insertSelective(secondnorm);
		if (count == 0) {
			secondnormOutputVo.getCommonOutputVo().setErrorMessage("插入数据不成功，新增一级分类失败！！");
		} else {
			secondnormOutputVo.getCommonOutputVo().setMessage("新增一级分类成功！！");
		}
		this.secondnormPage(secondnormOutputVo, secondnormInputVo);
		return secondnormOutputVo;
	}

	@Override
	public SecondnormOutputVo secondnormUpdate(SecondnormInputVo secondnormInputVo) {
		SecondnormOutputVo secondnormOutputVo = new SecondnormOutputVo();
		this.initItem(secondnormOutputVo, secondnormInputVo);
		secondnormInputVo.getCommonInputVo().setSelectBox(Constant.ZERO);
		Secondnorm secondnorm = secondnormMapper.selectByPrimaryKey(secondnormInputVo.getSecondnorm().getId());
		if (secondnormInputVo.getSecondnorm().getNormname().equals(secondnorm.getNormname())
				&& secondnormInputVo.getSecondnorm().getFirstnormid() == secondnorm.getFirstnormid()) {
			secondnormOutputVo.getCommonOutputVo().setWarMessage("二级分类信息无变化，无需修改二级分类信息！！");
		} else {
			if (StringUtils.isEmpty(secondnorm.getAbatetime())) {
				int count = secondnormMapper.updateByPrimaryKeySelective(secondnormInputVo.getSecondnorm());
				if (count == 0) {
					secondnormOutputVo.getCommonOutputVo().setErrorMessage("更新数据不成功，修改二级分类失败！！");
				} else {
					secondnormOutputVo.getCommonOutputVo().setMessage("修改二级分类成功！！");
				}
			} else {
				secondnormOutputVo.getCommonOutputVo().setErrorMessage("冻结的二级分类无法进行修改！！");
			}
		}
		this.secondnormPage(secondnormOutputVo, secondnormInputVo);
		return secondnormOutputVo;
	}

	@Override
	public SecondnormOutputVo secondnormFreeze(SecondnormInputVo secondnormInputVo) {
		SecondnormOutputVo secondnormOutputVo = new SecondnormOutputVo();
		this.initItem(secondnormOutputVo, secondnormInputVo);
		String deleteItem = secondnormInputVo.getCommonInputVo().getDeleteItem();
		if (Constant.ONE.equals(deleteItem)) {
			DetailnormExample example = new DetailnormExample();
			DetailnormExample.Criteria criteria = example.createCriteria();
			criteria.andDepartmentidEqualTo(secondnormInputVo.getSecondnorm().getId());
			List<Detailnorm> detailnorms = detailnormMapper.selectByExample(example);
			if (detailnorms == null || detailnorms.size() == 0) {
				int count = secondnormMapper.deleteByPrimaryKey(secondnormInputVo.getSecondnorm().getId());
				if (count == 0) {
					secondnormOutputVo.getCommonOutputVo().setErrorMessage("二级分类不存在，删除二级分类失败！！");
				} else {
					secondnormOutputVo.getCommonOutputVo().setMessage("删除二级分类成功！！");
					secondnormOutputVo.setSecondnorm((Secondnorm) Common.InitBean(Secondnorm.class));
				}
			} else {
				secondnormOutputVo.getCommonOutputVo().setWarMessage("二级分类在指标中已经被使用，无法删除，有必要请选择冻结！！");
			}
		} else {
			Secondnorm secondnorm = secondnormMapper.selectByPrimaryKey(secondnormInputVo.getSecondnorm().getId());
			if (!StringUtils.isEmpty(secondnorm)) {
				Date abatetime = secondnorm.getAbatetime();
				if (abatetime == null) {
					secondnorm.setAbatetime(new Date());
				} else {
					secondnorm.setAbatetime(null);
				}
				int count = secondnormMapper.updateByPrimaryKey(secondnorm);
				if (count == 0) {
					secondnormOutputVo.getCommonOutputVo().setErrorMessage("二级分类不存在，冻结/解冻二级分类失败！！");
				} else {
					secondnormOutputVo.getCommonOutputVo().setMessage("冻结/解冻二级分类成功！！");
				}
			}else {
				secondnormOutputVo.getCommonOutputVo().setErrorMessage("二级分类不存在，冻结/解冻二级分类失败！！");
			}
		}
		this.secondnormPage(secondnormOutputVo, secondnormInputVo);
		return secondnormOutputVo;
	}

	@Override
	public List<Secondnorm> getSecondnorms(int firstnormid){
		SecondnormExample example = new SecondnormExample();
		SecondnormExample.Criteria criteria = example.createCriteria();
		criteria.andAbatetimeIsNull();
		if (firstnormid != 0) {
			criteria.andFirstnormidEqualTo(firstnormid);
		}
		example.setOrderByClause("id");
		List<Secondnorm> secondnorms = secondnormMapper.selectByExample(example);
		if (secondnorms == null) {
			secondnorms = new ArrayList<Secondnorm>();
		}
		return secondnorms;
	}
	
	@Override
	public void secondnormReport(HttpServletRequest request, HttpServletResponse response, SecondnormInputVo secondnormInputVo) throws Exception {
		SecondnormOutputVo secondnormOutputVo = new SecondnormOutputVo();
		CommonOutputVo commonOutputVo = new CommonOutputVo();
		commonOutputVo.setPageFlag(Constant.ONE);
		secondnormOutputVo.setCommonOutputVo(commonOutputVo);
		this.secondnormPage(secondnormOutputVo, secondnormInputVo);
		String fileTitle = "二级分类基本信息" + Common.sysDate("yyyy-MM-dd");
		String[][] itemArray = {{"二级分类ID", "一级分类", "二级分类", "冻结日期", "创建日期"}, {"id","firstnormName","normname","abatetime","createtime"}};
		String servletPath = request.getSession().getServletContext().getRealPath("filePath") + File.separator + fileTitle + ".xls";
		Common.exportExcel(secondnormOutputVo.getSecondnormExts(), servletPath, itemArray, fileTitle);
		Common.download(servletPath, response);
		File file = new File(servletPath);
		if (file.exists()) {
			file.delete();
		}
	}
	
	private void secondnormPage(SecondnormOutputVo secondnormOutputVo, SecondnormInputVo secondnormInputVo) {
		SecondnormExample example = new SecondnormExample();
		List<SecondnormExt> secondnormExts = null;
		Page page = new Page();
		String normname = secondnormInputVo.getSecondnorm().getNormname();
		int firstnormid = secondnormInputVo.getSecondnorm().getFirstnormid();
		SecondnormExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(normname)) {
			if (Constant.ONE.equals(secondnormInputVo.getCommonInputVo().getSelectBox())) {
				criteria.andNormnameLike("%" + normname + "%");
			} else {
				criteria.andNormnameEqualTo(normname);
			}
		}
		
		if (firstnormid != 0) {
			criteria.andFirstnormidEqualTo(firstnormid);
		}
		int count = secondnormMapper.countByExample(example);
		if (count != 0) {
			page.setPerPage(Constant.PERPAGE);
			page.setCurPage(secondnormInputVo.getCommonInputVo().getCurPage());
			if (count%Constant.PERPAGE == 0){
				page.setCountPage(count/Constant.PERPAGE);
			} else {
				page.setCountPage(count/Constant.PERPAGE + 1);
			}
			if (Constant.ONE.equals(secondnormOutputVo.getCommonOutputVo().getPageFlag())) {
				example.setOrderByClause("a.id");
			} else {
				example.setOrderByClause(
						"a.id limit " + (page.getCurPage() -1)*Constant.PERPAGE + " , " + Constant.PERPAGE);
			}
			secondnormExts = secondnormExtMapper.selectByExample(example);
		} else {
			page = (Page) Common.InitBean(Page.class);
			page.setPerPage(Constant.PERPAGE);
			secondnormExts = new ArrayList<SecondnormExt>();
		}
		page.setCount(count);
		secondnormOutputVo.setSecondnormExts(secondnormExts);;
		secondnormOutputVo.getCommonOutputVo().setPage(page);
	}
	
	private void initItem(SecondnormOutputVo secondnormOutputVo, SecondnormInputVo secondnormInputVo) {
		CommonOutputVo commonOutputVo = new CommonOutputVo();
		CommonInputVo commonInputVo = secondnormInputVo.getCommonInputVo();
		commonOutputVo.setDeleteItem(commonInputVo.getDeleteItem());
		commonOutputVo.setSelectBox(commonInputVo.getSelectBox());
		secondnormOutputVo.setCommonOutputVo(commonOutputVo);
		secondnormOutputVo.setFirstnorms(firstnormService.getFirstnorms());
		secondnormOutputVo.setSecondnorm(secondnormInputVo.getSecondnorm());
	}
}
