package com.yx.ssm.po;

import org.apache.poi.ss.usermodel.Workbook;

public class ExcelInfo {

	// 当前行
	private int currPosition;
	
	// sheet数量
	private int sheetNumber;
	
	// 工作簿
	private Workbook workbook;

	public int getCurrPosition() {
		return currPosition;
	}

	public void setCurrPosition(int currPosition) {
		this.currPosition = currPosition;
	}

	public int getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(int sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}
}
