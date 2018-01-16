package com.yx.ssm.service.action;

import java.util.List;

import com.yx.ssm.po.Department;
import com.yx.ssm.po.DepartmentExample;
import com.yx.ssm.po.DetailnormExt;
import com.yx.ssm.po.Firstnorm;
import com.yx.ssm.po.FirstnormExample;
import com.yx.ssm.po.Secondnorm;
import com.yx.ssm.po.SecondnormExt;
import com.yx.ssm.service.service.DepartmentDao;
import com.yx.ssm.service.service.DetailnormDao;
import com.yx.ssm.service.service.FirstnormDao;
import com.yx.ssm.service.service.SecondnormDao;
import com.yx.ssm.vo.FileUploadOutput;

public class FileHandle {

	private GetApplicationContext getApplicationContext;
	public FileHandle() {
		getApplicationContext = new GetApplicationContext();
	}
	
	public void checkFile(FileUploadOutput fileUploadOutput) {

		List<DetailnormExt> detailnormExts = fileUploadOutput.getDetailnormExts();
		List<String> messages = fileUploadOutput.getMessages();
		if (messages.size() > 0) {
			return;
		}
		// 数据检证
		for (DetailnormExt detailnormExt : detailnormExts) {

			// 行数
			int index = detailnormExt.getId();

			// 部门检证
			DepartmentExample departmentExample = new DepartmentExample();
			DepartmentExample.Criteria departmentCriteria = departmentExample.createCriteria();
			departmentCriteria.andDpnameEqualTo(detailnormExt.getDpName());
			departmentCriteria.andAbatetimeIsNull();
			List<Department> departments = ((DepartmentDao) getApplicationContext.excute(
					DepartmentDao.class)).selectByExample(departmentExample);
			if (departments.size() == 0) {
				messages.add("第" + index + "行不存在部门（" + detailnormExt.getDpName() + "）！！");
			} else {
				detailnormExt.setDepartmentid(departments.get(0).getId());
			}
			
			// 一级分类检证
			FirstnormExample firstnormExample = new FirstnormExample();
			FirstnormExample.Criteria firstnormCriteria = firstnormExample.createCriteria();
			firstnormCriteria.andNormnameEqualTo(detailnormExt.getFirstnormName());
			firstnormCriteria.andAbatetimeIsNull();
			List<Firstnorm> firstnorms = ((FirstnormDao) getApplicationContext.excute(
					FirstnormDao.class)).selectByExample(firstnormExample);
			if (firstnorms.size() == 0) {
				messages.add("第" + index + "行不存在一级分类（" + detailnormExt.getFirstnormName() + "）！！");
			}
			
			// 二级分类检证
			SecondnormExt secondnormExt = new SecondnormExt();
			secondnormExt.setFirstnormName(detailnormExt.getFirstnormName());
			secondnormExt.setNormname(detailnormExt.getSecondnormName());
			List<Secondnorm> secondnorms = ((SecondnormDao) getApplicationContext.excute(
					SecondnormDao.class)).selectByExample(secondnormExt);
			if (secondnorms.size() != 1) {
				messages.add("第" + index + "行的二级分类（" + detailnormExt.getSecondnormName() 
				+ "）与一级分类（" + detailnormExt.getFirstnormName() + "）的关联关系有误，请确认二级分类是否正确！！");
			}else {
				detailnormExt.setFirstnormid(secondnorms.get(0).getFirstnormid());
				detailnormExt.setSecondnormid(secondnorms.get(0).getId());
			}
		}
		fileUploadOutput.setMessages(messages);
		fileUploadOutput.setDetailnormExts(detailnormExts);
	}

	public void excute(FileUploadOutput fileUploadOutput) {

		List<DetailnormExt> detailnormExts = fileUploadOutput.getDetailnormExts();
		if (fileUploadOutput.getMessages().size() > 0) {
			return;
		} else {
			fileUploadOutput.setMessages(null);
		}
		int count = ((DetailnormDao) getApplicationContext.excute(DetailnormDao.class)).insertSelective(detailnormExts);
		if (count == detailnormExts.size()) {
			fileUploadOutput.setMessage("指标导入成功！！");
		}
	}
}