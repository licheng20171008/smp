package com.yx.ssm.service.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yx.ssm.po.DetailnormExt;
import com.yx.ssm.po.ExcelInfo;
import com.yx.ssm.vo.FileUploadOutput;

public class FileReader {

	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, FileUploadOutput fileUploadOutput) throws IOException, FileUploadException {
		
		// 文件二进制输入流
		InputStream is = null;
		
		// 文件名
		String fileName = "";
		
		// 文件类型
		String fileType = "";
		
		// 页面消息返回
		List<String> messages = new ArrayList<String>();
		
		// 文件信息
		ExcelInfo excelInfo = new ExcelInfo();
		
		// 使用Apache文件上传组件处理文件上传步骤
		// 1.创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 2.创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 解决上传文件名的中文乱码
		upload.setHeaderEncoding("UTF-8");
		
		// 3.判断提交上来的数据是否是上传表单的数据
		if (!ServletFileUpload.isMultipartContent(request)) {
			
			// 按照传统方式获取数据
			messages.add("文件上传方式错误！！");
			return;
		}
		
		// 4.使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合
		// 每一个FileItem对应一个Form表单的输入项

		List<FileItem> fileItemList = upload.parseRequest(request);
		for (FileItem fileItem : fileItemList) {

			// 判断上传文件
			if (!fileItem.isFormField()) {
				fileName = fileItem.getName();
				if (fileName == null || Constant.EMPTY.equals(fileName.trim())) {
					messages.add("请上传文件！！");
					fileUploadOutput.setMessages(messages);
					return;
				} else {

					// 注意：不同的浏览器提交的文件名不一样，去除路径
					// 处理获得的上传文件的文件名的路径部分，只保留文件名部分
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

					// 取得文件名的后缀名赋值给filetype
					fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

					// 判断文件类型
					//  && !fileType.equalsIgnoreCase("xls")
					if (!fileType.equalsIgnoreCase("xlsx")) {
						messages.add("请上传EXCEL文件！！");
						fileUploadOutput.setMessages(messages);
						return;
					} else {
						// 设置开始为0
						excelInfo.setSheetNumber(0);

						// 创建文件输入流
						is = fileItem.getInputStream();

						// 如果是EXCEL文件则创建HSSFWorkbook读取
						excelInfo.setWorkbook(new XSSFWorkbook(is));

						// 设置Sheet数
						excelInfo.setSheetNumber(excelInfo.getWorkbook().getNumberOfSheets());
						fileUploadOutput.setExcelInfo(excelInfo);

						// 读取数据
						this.readLine(fileUploadOutput);
						if (is != null) {
							is.close();
						}
					}
					fileUploadOutput.setMessages(messages);
					break;
				}
			}
		}
	}

	// 函数readLine读取文件的一行
	private void readLine(FileUploadOutput fileUploadOutput) throws IOException {

		// 当前sheet
		int currSheet = 0;
		
		// 初始化SHEET
		XSSFSheet sheet = null;
		ExcelInfo excelInfo = fileUploadOutput.getExcelInfo();

		// 判断是否还有Sheet
		while (currSheet < excelInfo.getSheetNumber()) {

			// 根据currSheet值获得当前的SHEET
			sheet = (XSSFSheet) excelInfo.getWorkbook().getSheetAt(currSheet);
			fileUploadOutput.setDetailnormExts(new ArrayList<DetailnormExt>());

			// 初始化当前SHEET位置
			excelInfo.setCurrPosition(0);

			// 读取当前行数据
			while (excelInfo.getCurrPosition() <= sheet.getLastRowNum()) {
				// 获取当前行数
				int row = excelInfo.getCurrPosition();
				excelInfo.setCurrPosition(excelInfo.getCurrPosition() + 1);

				// 读取当前行数据
				this.getXssfSheetLine(sheet, row, fileUploadOutput);
			}
			// 当前行数是否已经到达文件末尾
			if (excelInfo.getCurrPosition() == (sheet.getLastRowNum() + 1)) {

				// 当前SHEET指向下一张sheet
				currSheet++;
				continue;
			}
		}
	}

	// 函数getLine返回Sheet的一行数据
	private void getXssfSheetLine(XSSFSheet sheet, int row, FileUploadOutput fileUploadOutput) {

		DetailnormExt detailnormExt = new DetailnormExt();
		
		// 根据行数取得sheet的一行
		XSSFRow rowline = sheet.getRow(row);
		
		// 单元格获取
		XSSFCell cell = null;

		// 循环遍历所有列
		if (row > 1) {
			
			// 行数
			detailnormExt.setId(row + 1);
			
			// 一级分类
			cell = rowline.getCell(0);
			detailnormExt.setFirstnormName(getCellValue(cell));
			
			// 二级分类
			cell = rowline.getCell(1);
			detailnormExt.setSecondnormName(getCellValue(cell));
			
			// 编号
			cell = rowline.getCell(2);
			detailnormExt.setNormid(getCellValue(cell));
			
			// 指标名称
			cell = rowline.getCell(3);
			detailnormExt.setNormname(getCellValue(cell));
			
			// 指标定义
			cell = rowline.getCell(4);
			detailnormExt.setNormdescription(getCellValue(cell));
			
			// 计算公式
			cell = rowline.getCell(5);
			detailnormExt.setNormformula(getCellValue(cell));
			
			// 统计周期
			cell = rowline.getCell(6);
			detailnormExt.setComputingcycle(Float.parseFloat(getCellValue(cell)));
			
			// 周期单位
			cell = rowline.getCell(7);
			detailnormExt.setCycleunit(getCellValue(cell));
			
			// 部门
			cell = rowline.getCell(8);
			detailnormExt.setDpName(getCellValue(cell));
			
			// 创建日期
			detailnormExt.setCreatetime(new Date());
			fileUploadOutput.getDetailnormExts().add(detailnormExt);
		}
	}

	private String getCellValue(XSSFCell cell) {
		
		String cellvalue = Constant.EMPTY;
		if (cell != null) {
			// 判断当前cell的type
			switch (cell.getCellType()) {
			// 如果当前cell的type为NUMERIC
			case XSSFCell.CELL_TYPE_NUMERIC: {
				// 判断当前cell是否为date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {

					// 把date转换成本地格式的字符串
					cellvalue = cell.getDateCellValue().toString();
				} else {
					// 如果是纯数字
					// 取得当前cell的数值
					Integer num = new Integer((int) cell.getNumericCellValue());
					cellvalue = String.valueOf(num);
				}
				break;

				// 如果当前cell的type为STRING
			}
			case HSSFCell.CELL_TYPE_STRING: {
				// 取得当前的cell字符串
				cellvalue = cell.getStringCellValue().replaceAll("'", "");
				for(int j=10;j<14;j++){  
					cellvalue = cellvalue.replaceAll(String.valueOf((char)j), "");  
			    }
				break;
			}
			}
		}
		return cellvalue;
	}
}
