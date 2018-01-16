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
import com.yx.ssm.mapper.FirstnormMapper;
import com.yx.ssm.mapper.SecondnormMapper;
import com.yx.ssm.po.Detailnorm;
import com.yx.ssm.po.DetailnormExample;
import com.yx.ssm.po.Firstnorm;
import com.yx.ssm.po.FirstnormExample;
import com.yx.ssm.po.Page;
import com.yx.ssm.po.Secondnorm;
import com.yx.ssm.po.SecondnormExample;
import com.yx.ssm.service.action.Common;
import com.yx.ssm.service.action.Constant;
import com.yx.ssm.service.service.FirstnormService;
import com.yx.ssm.vo.CommonInputVo;
import com.yx.ssm.vo.CommonOutputVo;
import com.yx.ssm.vo.FirstnormInputVo;
import com.yx.ssm.vo.FirstnormOutputVo;

public class FirstnormServiceImpl implements FirstnormService {

	@Autowired
	private FirstnormMapper firstnormMapper;
	
	@Autowired
	private DetailnormMapper detailnormMapper;
	
	@Autowired
	private SecondnormMapper secondnormMapper;
	
	@Override
	public FirstnormOutputVo init() {
		FirstnormOutputVo firstnormOutputVo = new FirstnormOutputVo();
		
		// 显示列表初始化
		firstnormOutputVo.setFirstnorms(null);
				
		// 项目初始化
		firstnormOutputVo.setFirstnorm((Firstnorm) Common.InitBean(Firstnorm.class));
		CommonOutputVo commonOutputVo = new CommonOutputVo();
				
		// 信息初始化
		commonOutputVo.setMessage(Constant.EMPTY);
		
		// 查询项目
		commonOutputVo.setSelectBox(Constant.ONE);
		
		// 删除项目
		commonOutputVo.setDeleteItem(Constant.ZERO);
		
		// 分页项目
		commonOutputVo.setPage((Page) Common.InitBean(Page.class));
		firstnormOutputVo.setCommonOutputVo(commonOutputVo);
		
		return firstnormOutputVo;
	}

	@Override
	public FirstnormOutputVo firstnormSelect(FirstnormInputVo firstnormInputVo) {
		FirstnormOutputVo firstnormOutputVo = new FirstnormOutputVo();
		this.initItem(firstnormOutputVo, firstnormInputVo);
		this.firstnormPage(firstnormOutputVo, firstnormInputVo);
		return firstnormOutputVo;
	}

	@Override
	public FirstnormOutputVo firstnormInsert(FirstnormInputVo firstnormInputVo) {
		FirstnormOutputVo firstnormOutputVo = new FirstnormOutputVo();
		this.initItem(firstnormOutputVo, firstnormInputVo);
		firstnormInputVo.getCommonInputVo().setSelectBox(Constant.ZERO);
		Firstnorm firstnorm = firstnormInputVo.getFirstnorm();
		firstnorm.setCreatetime(new Date());
		int count = firstnormMapper.insertSelective(firstnorm);
		if (count == 0) {
			firstnormOutputVo.getCommonOutputVo().setErrorMessage("插入数据不成功，新增一级分类失败！！");
		} else {
			firstnormOutputVo.getCommonOutputVo().setMessage("新增一级分类成功！！");
		}
		this.firstnormPage(firstnormOutputVo, firstnormInputVo);
		return firstnormOutputVo;
	}

	@Override
	public FirstnormOutputVo firstnormUpdate(FirstnormInputVo firstnormInputVo) {
		FirstnormOutputVo firstnormOutputVo = new FirstnormOutputVo();
		this.initItem(firstnormOutputVo, firstnormInputVo);
		firstnormInputVo.getCommonInputVo().setSelectBox(Constant.ZERO);
		Firstnorm firstnorm = firstnormMapper.selectByPrimaryKey(firstnormInputVo.getFirstnorm().getId());
		if (firstnormInputVo.getFirstnorm().getNormname().equals(firstnorm.getNormname())) {
			firstnormOutputVo.getCommonOutputVo().setWarMessage("一级分类信息无变化，无需修改一级分类信息！！");
		} else {
			if (StringUtils.isEmpty(firstnorm.getAbatetime())) {
				int count = firstnormMapper.updateByPrimaryKeySelective(firstnormInputVo.getFirstnorm());
				if (count == 0) {
					firstnormOutputVo.getCommonOutputVo().setErrorMessage("更新数据不成功，修改一级分类失败！！");
				} else {
					firstnormOutputVo.getCommonOutputVo().setMessage("修改一级分类成功！！");
				}
			} else {
				firstnormOutputVo.getCommonOutputVo().setErrorMessage("冻结的一级分类信息无法进行修改！！");
			}
		}
		this.firstnormPage(firstnormOutputVo, firstnormInputVo);
		return firstnormOutputVo;
	}

	@Override
	public FirstnormOutputVo firstnormFreeze(FirstnormInputVo firstnormInputVo) {
		FirstnormOutputVo firstnormOutputVo = new FirstnormOutputVo();
		this.initItem(firstnormOutputVo, firstnormInputVo);
		String deleteItem = firstnormInputVo.getCommonInputVo().getDeleteItem();
		if (Constant.ONE.equals(deleteItem)) {
			DetailnormExample detailnormExample = new DetailnormExample();
			DetailnormExample.Criteria detailnormCriteria = detailnormExample.createCriteria();
			detailnormCriteria.andFirstnormidEqualTo(firstnormInputVo.getFirstnorm().getId());
			List<Detailnorm> detailnorms = detailnormMapper.selectByExample(detailnormExample);
			if (detailnorms != null && detailnorms.size() == 0) {
				firstnormOutputVo.getCommonOutputVo().setWarMessage("一级分类在指标中已经被使用，无法删除，有必要请选择冻结！！");
				this.firstnormPage(firstnormOutputVo, firstnormInputVo);
				return firstnormOutputVo;
			}
			
			SecondnormExample secondnormExample = new SecondnormExample();
			SecondnormExample.Criteria secondnormCriteria = secondnormExample.createCriteria();
			secondnormCriteria.andFirstnormidEqualTo(firstnormInputVo.getFirstnorm().getId());
			List<Secondnorm> secondnorms = secondnormMapper.selectByExample(secondnormExample);
			if (secondnorms == null || secondnorms.size() == 0) {
				int count = firstnormMapper.deleteByPrimaryKey(firstnormInputVo.getFirstnorm().getId());
				if (count == 0) {
					firstnormOutputVo.getCommonOutputVo().setErrorMessage("一级分类不存在，删除一级分类失败！！");
				} else {
					firstnormOutputVo.getCommonOutputVo().setMessage("删除一级分类成功！！");
					firstnormOutputVo.setFirstnorm((Firstnorm) Common.InitBean(Firstnorm.class));
				}
			} else {
				firstnormOutputVo.getCommonOutputVo().setWarMessage("一级分类在二级分类中已经被使用，无法删除，有必要请选择冻结！！");
			}
		} else {
			Firstnorm firstnorm = firstnormMapper.selectByPrimaryKey(firstnormInputVo.getFirstnorm().getId());
			if (!StringUtils.isEmpty(firstnorm)) {
				Date abatetime = firstnorm.getAbatetime();
				if (abatetime == null) {
					firstnorm.setAbatetime(new Date());
				} else {
					firstnorm.setAbatetime(null);
				}
				int count = firstnormMapper.updateByPrimaryKey(firstnorm);
				if (count == 0) {
					firstnormOutputVo.getCommonOutputVo().setErrorMessage("一级分类不存在，冻结/解冻一级分类失败！！");
				} else {
					firstnormOutputVo.getCommonOutputVo().setMessage("冻结/解冻一级分类成功！！");
				}
			}else {
				firstnormOutputVo.getCommonOutputVo().setErrorMessage("一级分类不存在，冻结/解冻一级分类失败！！");
			}
		}
		this.firstnormPage(firstnormOutputVo, firstnormInputVo);
		return firstnormOutputVo;
	}

	@Override
	public void firstnormReport(HttpServletRequest request, HttpServletResponse response, FirstnormInputVo firstnormInputVo) throws Exception {
		FirstnormOutputVo firstnormOutputVo = new FirstnormOutputVo();
		CommonOutputVo commonOutputVo = new CommonOutputVo();
		commonOutputVo.setPageFlag(Constant.ONE);
		firstnormOutputVo.setCommonOutputVo(commonOutputVo);
		this.firstnormPage(firstnormOutputVo, firstnormInputVo);
		String fileTitle = "一级分类基本信息" + Common.sysDate("yyyy-MM-dd");
		String[][] itemArray = {{"一级分类ID", "一级分类名称", "冻结日期", "创建日期"}, {"id","normname","abatetime","createtime"}};
		String servletPath = request.getSession().getServletContext().getRealPath("filePath") + File.separator + fileTitle + ".xls";
		Common.exportExcel(firstnormOutputVo.getFirstnorms(), servletPath, itemArray, fileTitle);
		Common.download(servletPath, response);
		File file = new File(servletPath);
		if (file.exists()) {
			file.delete();
		}
	}

	@Override
	public List<Firstnorm> getFirstnorms(){
		FirstnormExample example = new FirstnormExample();
		FirstnormExample.Criteria criteria = example.createCriteria();
		criteria.andAbatetimeIsNull();
		example.setOrderByClause("id");
		List<Firstnorm> firstnorms = firstnormMapper.selectByExample(example);
		if (firstnorms == null) {
			firstnorms = new ArrayList<Firstnorm>();
		}
		return firstnorms;
	}
	
	private void firstnormPage(FirstnormOutputVo firstnormOutputVo, FirstnormInputVo firstnormInputVo) {
		FirstnormExample example = new FirstnormExample();
		List<Firstnorm> firstnorms = null;
		Page page = new Page();
		String normname = firstnormInputVo.getFirstnorm().getNormname();
		FirstnormExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(normname)) {
			if (Constant.ONE.equals(firstnormInputVo.getCommonInputVo().getSelectBox())) {
				criteria.andNormnameLike("%" + normname + "%");
			} else {
				criteria.andNormnameEqualTo(normname);
			}
		}
		
		int count = firstnormMapper.countByExample(example);
		if (count != 0) {
			page.setPerPage(Constant.PERPAGE);
			page.setCurPage(firstnormInputVo.getCommonInputVo().getCurPage());
			if (count%Constant.PERPAGE == 0){
				page.setCountPage(count/Constant.PERPAGE);
			} else {
				page.setCountPage(count/Constant.PERPAGE + 1);
			}
			if (Constant.ONE.equals(firstnormOutputVo.getCommonOutputVo().getPageFlag())) {
				example.setOrderByClause("id");
			} else {
				example.setOrderByClause(
						"id limit " + (page.getCurPage() -1)*Constant.PERPAGE + " , " + Constant.PERPAGE);
			}
			firstnorms = firstnormMapper.selectByExample(example);
		} else {
			page = (Page) Common.InitBean(Page.class);
			page.setPerPage(Constant.PERPAGE);
			firstnorms = new ArrayList<Firstnorm>();
		}
		page.setCount(count);
		firstnormOutputVo.setFirstnorms(firstnorms);
		firstnormOutputVo.getCommonOutputVo().setPage(page);
	}
	
	private void initItem(FirstnormOutputVo firstnormOutputVo, FirstnormInputVo firstnormInputVo) {
		CommonOutputVo commonOutputVo = new CommonOutputVo();
		CommonInputVo commonInputVo = firstnormInputVo.getCommonInputVo();
		commonOutputVo.setDeleteItem(commonInputVo.getDeleteItem());
		commonOutputVo.setSelectBox(commonInputVo.getSelectBox());
		firstnormOutputVo.setCommonOutputVo(commonOutputVo);
		firstnormOutputVo.setFirstnorm(firstnormInputVo.getFirstnorm());
	}
}
