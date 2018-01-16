package com.yx.ssm.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yx.ssm.mapper.DetailnormExtMapper;
import com.yx.ssm.mapper.DetailnormMapper;
import com.yx.ssm.po.Detailnorm;
import com.yx.ssm.po.DetailnormExample;
import com.yx.ssm.po.DetailnormExt;
import com.yx.ssm.po.Page;
import com.yx.ssm.service.action.Common;
import com.yx.ssm.service.action.Constant;
import com.yx.ssm.service.service.DepartmentService;
import com.yx.ssm.service.service.DetailnormService;
import com.yx.ssm.service.service.FirstnormService;
import com.yx.ssm.service.service.SecondnormService;
import com.yx.ssm.vo.CommonInputVo;
import com.yx.ssm.vo.CommonOutputVo;
import com.yx.ssm.vo.DetailnormInputVo;
import com.yx.ssm.vo.DetailnormOutputVo;

public class DetailnormServiceImpl implements DetailnormService {

	@Autowired
	private FirstnormService firstnormService;
	
	@Autowired
	private SecondnormService secondnormService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private DetailnormMapper detailnormMapper;
	
	@Autowired
	private DetailnormExtMapper detailnormExtMapper;
	
	@Override
	public DetailnormOutputVo init() {
		DetailnormOutputVo detailnormOutputVo = new DetailnormOutputVo();
		
		// 显示列表初始化
		detailnormOutputVo.setDetailnormExts(null);;
				
		// 项目初始化
		detailnormOutputVo.setDetailnorm((Detailnorm) Common.InitBean(Detailnorm.class));
		
		// 一级分类选项
		detailnormOutputVo.setFirstnorms(firstnormService.getFirstnorms());
		
		// 二级分类选项
		detailnormOutputVo.setSecondnorms(secondnormService.getSecondnorms(0));
		
		// 部门选项
		detailnormOutputVo.setDepartments(departmentService.getDepartments());
		CommonOutputVo commonOutputVo = new CommonOutputVo();
				
		// 信息初始化
		commonOutputVo.setMessage(Constant.EMPTY);
		
		// 查询项目
		commonOutputVo.setSelectBox(Constant.ONE);
		
		// 删除项目
		commonOutputVo.setDeleteItem(Constant.ZERO);
		
		// 分页项目
		commonOutputVo.setPage((Page) Common.InitBean(Page.class));
		detailnormOutputVo.setCommonOutputVo(commonOutputVo);
		
		return detailnormOutputVo;
	}

	@Override
	public DetailnormOutputVo detailnormSelect(DetailnormInputVo detailnormInputVo) {
		DetailnormOutputVo detailnormOutputVo = new DetailnormOutputVo();
		this.initItem(detailnormOutputVo, detailnormInputVo);
		this.detailnormPage(detailnormOutputVo, detailnormInputVo);
		return detailnormOutputVo;
	}

	@Override
	public DetailnormOutputVo detailnormInsert(DetailnormInputVo detailnormInputVo) {
		DetailnormOutputVo detailnormOutputVo = new DetailnormOutputVo();
		this.initItem(detailnormOutputVo, detailnormInputVo);
		detailnormInputVo.getCommonInputVo().setSelectBox(Constant.ZERO);
		Detailnorm detailnorm = detailnormInputVo.getDetailnorm();
		detailnorm.setCreatetime(new Date());
		int count = detailnormMapper.insertSelective(detailnorm);
		if (count == 0) {
			detailnormOutputVo.getCommonOutputVo().setErrorMessage("插入数据不成功，新增指标失败！！");
		} else {
			detailnormOutputVo.getCommonOutputVo().setMessage("新增指标成功！！");
		}
		this.detailnormPage(detailnormOutputVo, detailnormInputVo);
		return detailnormOutputVo;
	}

	@Override
	public DetailnormOutputVo detailnormUpdate(DetailnormInputVo detailnormInputVo) {
		DetailnormOutputVo detailnormOutputVo = new DetailnormOutputVo();
		this.initItem(detailnormOutputVo, detailnormInputVo);
		detailnormInputVo.getCommonInputVo().setSelectBox(Constant.ZERO);
		Detailnorm detailnorm = detailnormMapper.selectByPrimaryKey(detailnormInputVo.getDetailnorm().getId());
		if (detailnormInputVo.getDetailnorm().getFirstnormid() == detailnorm.getFirstnormid()
				&& detailnormInputVo.getDetailnorm().getSecondnormid() == detailnorm.getSecondnormid()
				&& detailnormInputVo.getDetailnorm().getNormid() == detailnorm.getNormid()
				&& detailnormInputVo.getDetailnorm().getNormname().equals(detailnorm.getNormname())
				&& detailnormInputVo.getDetailnorm().getNormdescription().equals(detailnorm.getNormdescription())
				&& detailnormInputVo.getDetailnorm().getNormformula().equals(detailnorm.getNormformula())
				&& detailnormInputVo.getDetailnorm().getComputingcycle() == detailnorm.getComputingcycle()
				&& detailnormInputVo.getDetailnorm().getCycleunit().equals(detailnorm.getCycleunit())
				&& detailnormInputVo.getDetailnorm().getDepartmentid() == detailnorm.getDepartmentid()
				&& detailnormInputVo.getDetailnorm().getRemark().equals(detailnorm.getRemark())) {
			detailnormOutputVo.getCommonOutputVo().setWarMessage("指标信息无变化，无需修改指标信息！！");
		} else {
			if (StringUtils.isEmpty(detailnorm.getAbatetime())) {
				int count = detailnormMapper.updateByPrimaryKeySelective(detailnormInputVo.getDetailnorm());
				if (count == 0) {
					detailnormOutputVo.getCommonOutputVo().setErrorMessage("更新数据不成功，修改指标失败！！");
				} else {
					detailnormOutputVo.getCommonOutputVo().setMessage("修改指标成功！！");
				}
			} else {
				detailnormOutputVo.getCommonOutputVo().setErrorMessage("冻结的指标无法进行修改！！");
			}
		}
		this.detailnormPage(detailnormOutputVo, detailnormInputVo);
		return detailnormOutputVo;
	}

	@Override
	public DetailnormOutputVo detailnormFreeze(DetailnormInputVo detailnormInputVo) {
		DetailnormOutputVo detailnormOutputVo = new DetailnormOutputVo();
		this.initItem(detailnormOutputVo, detailnormInputVo);
		String deleteItem = detailnormInputVo.getCommonInputVo().getDeleteItem();
		if (Constant.ONE.equals(deleteItem)) {
			int count = detailnormMapper.deleteByPrimaryKey(detailnormInputVo.getDetailnorm().getId());
			if (count == 0) {
				detailnormOutputVo.getCommonOutputVo().setErrorMessage("指标不存在，删除指标失败！！");
			} else {
				detailnormOutputVo.getCommonOutputVo().setMessage("删除指标成功！！");
				detailnormOutputVo.setDetailnorm((Detailnorm) Common.InitBean(Detailnorm.class));
			}
		} else {
			Detailnorm detailnorm = detailnormMapper.selectByPrimaryKey(detailnormInputVo.getDetailnorm().getId());
			if (!StringUtils.isEmpty(detailnorm)) {
				Date abatetime = detailnorm.getAbatetime();
				if (abatetime == null) {
					detailnorm.setAbatetime(new Date());
				} else {
					detailnorm.setAbatetime(null);
				}
				int count = detailnormMapper.updateByPrimaryKey(detailnorm);
				if (count == 0) {
					detailnormOutputVo.getCommonOutputVo().setErrorMessage("指标不存在，冻结/解冻指标失败！！");
				} else {
					detailnormOutputVo.getCommonOutputVo().setMessage("冻结/解冻指标成功！！");
				}
			}else {
				detailnormOutputVo.getCommonOutputVo().setErrorMessage("指标不存在，冻结/解冻指标失败！！");
			}
		}
		this.detailnormPage(detailnormOutputVo, detailnormInputVo);
		return detailnormOutputVo;
	}

	@Override
	public void detailnormReport(HttpServletRequest request, HttpServletResponse response, DetailnormInputVo detailnormInputVo) throws Exception {
		DetailnormOutputVo detailnormOutputVo = new DetailnormOutputVo();
		CommonOutputVo commonOutputVo = new CommonOutputVo();
		commonOutputVo.setPageFlag(Constant.ONE);
		detailnormOutputVo.setCommonOutputVo(commonOutputVo);
		this.detailnormPage(detailnormOutputVo, detailnormInputVo);
		String fileTitle = "指标基本信息" + Common.sysDate("yyyy-MM-dd");
		String[][] itemArray = {{"指标ID", "一级分类", "二级分类", "指标编号", "指标名称", "指标定义", "计算公式", "统计周期", "周期单位", "部门","备注" , "冻结日期", "创建日期"}, 
				{"id","firstnormName","secondnormName","normid","normname","normdescription","normformula","computingcycle","cycleunit","dpname","remark","abatetime","createtime"}};
		String servletPath = request.getSession().getServletContext().getRealPath("filePath") + File.separator + fileTitle + ".xls";
		Common.exportExcel(detailnormOutputVo.getDetailnormExts(), servletPath, itemArray, fileTitle);
		Common.download(servletPath, response);
		File file = new File(servletPath);
		if (file.exists()) {
			file.delete();
		}
	}

	private void detailnormPage(DetailnormOutputVo detailnormOutputVo, DetailnormInputVo detailnormInputVo) {
		DetailnormExample example = new DetailnormExample();
		List<DetailnormExt> detailnormExts = null;
		Page page = new Page();
		Detailnorm detailnorm = detailnormInputVo.getDetailnorm();
		DetailnormExample.Criteria criteria = example.createCriteria();
		String selectBox = detailnormInputVo.getCommonInputVo().getSelectBox();
		
		// 一级分类项目
		int firstnormid = detailnorm.getFirstnormid();
		if (firstnormid != 0) {
			criteria.andFirstnormidEqualTo(firstnormid);
		}
		
		// 二级分类项目
		int secondnormid = detailnorm.getSecondnormid();
		if (secondnormid != 0) {
			criteria.andSecondnormidEqualTo(secondnormid);
		}
		
		// 指标编号
		String normid = detailnorm.getNormid();
		if (!StringUtils.isEmpty(normid)) {
			if (Constant.ONE.equals(selectBox)) {
				criteria.andNormidLike("%" + normid + "%");
			} else {
				criteria.andNormidEqualTo(normid);
			}
		}
		
		// 指标名称
		String normname = detailnorm.getNormname();
		if (!StringUtils.isEmpty(normname)) {
			if (Constant.ONE.equals(selectBox)) {
				criteria.andNormnameLike("%" + normname + "%");
			} else {
				criteria.andNormnameEqualTo(normname);
			}
		}
		
		// 指标定义
		String normdescription = detailnorm.getNormdescription();
		if (!StringUtils.isEmpty(normdescription)) {
			if (Constant.ONE.equals(selectBox)) {
				criteria.andNormdescriptionLike("%" + normdescription + "%");
			} else {
				criteria.andNormdescriptionEqualTo(normdescription);
			}
		}
		
		// 计算公式
		String normformula = detailnorm.getNormformula();
		if (!StringUtils.isEmpty(normformula)) {
			if (Constant.ONE.equals(selectBox)) {
				criteria.andNormformulaLike("%" + normformula + "%");
			} else {
				criteria.andNormformulaEqualTo(normformula);
			}
		}
		
		// 统计周期
		if (detailnorm.getComputingcycle() != null && detailnorm.getComputingcycle() != 0) {
			criteria.andComputingcycleEqualTo(detailnorm.getComputingcycle());
		}
		
		// 周期单位
		String cycleunit = detailnorm.getCycleunit();
		if (!StringUtils.isEmpty(cycleunit)) {
			if (Constant.ONE.equals(selectBox)) {
				criteria.andCycleunitLike("%" + cycleunit + "%");
			} else {
				criteria.andCycleunitEqualTo(cycleunit);
			}
		}
		
		// 部门
		int departmentid = detailnorm.getDepartmentid();
		if (departmentid != 0) {
			criteria.andDepartmentidEqualTo(departmentid);
		}
		
		// 备注
		String remark = detailnorm.getRemark();
		if (!StringUtils.isEmpty(remark)) {
			if (Constant.ONE.equals(selectBox)) {
				criteria.andRemarkLike("%" + remark + "%");
			} else {
				criteria.andRemarkEqualTo(remark);
			}
		}
		
		int count = detailnormMapper.countByExample(example);
		if (count != 0) {
			page.setPerPage(Constant.PERPAGE);
			page.setCurPage(detailnormInputVo.getCommonInputVo().getCurPage());
			if (count%Constant.PERPAGE == 0){
				page.setCountPage(count/Constant.PERPAGE);
			} else {
				page.setCountPage(count/Constant.PERPAGE + 1);
			}
			if (Constant.ONE.equals(detailnormOutputVo.getCommonOutputVo().getPageFlag())) {
				example.setOrderByClause("a.id");
			} else {
				example.setOrderByClause(
						"a.id limit " + (page.getCurPage() -1)*Constant.PERPAGE + " , " + Constant.PERPAGE);
			}
			detailnormExts = detailnormExtMapper.selectByExample(example);
		} else {
			page = (Page) Common.InitBean(Page.class);
			page.setPerPage(Constant.PERPAGE);
			detailnormExts = new ArrayList<DetailnormExt>();
		}
		page.setCount(count);
		detailnormOutputVo.setDetailnormExts(detailnormExts);;
		detailnormOutputVo.getCommonOutputVo().setPage(page);
	}
	
	private void initItem(DetailnormOutputVo detailnormOutputVo, DetailnormInputVo detailnormInputVo) {
		CommonOutputVo commonOutputVo = new CommonOutputVo();
		CommonInputVo commonInputVo = detailnormInputVo.getCommonInputVo();
		commonOutputVo.setDeleteItem(commonInputVo.getDeleteItem());
		commonOutputVo.setSelectBox(commonInputVo.getSelectBox());
		detailnormOutputVo.setCommonOutputVo(commonOutputVo);
		detailnormOutputVo.setFirstnorms(firstnormService.getFirstnorms());
		detailnormOutputVo.setSecondnorms(secondnormService.getSecondnorms(
			detailnormInputVo.getDetailnorm().getFirstnormid()));
		detailnormOutputVo.setDepartments(departmentService.getDepartments());
		detailnormOutputVo.setDetailnorm(detailnormInputVo.getDetailnorm());
	}
}
