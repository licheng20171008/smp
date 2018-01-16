package com.yx.ssm.vo;

import com.yx.ssm.po.Page;

public class CommonOutputVo {

    private String selectBox;
	
	private String deleteItem;
	
	private Page page;
	
	private String errorMessage;
	private String warMessage;
	private String message;
	
	private String pageFlag;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSelectBox() {
		return selectBox;
	}

	public void setSelectBox(String selectBox) {
		this.selectBox = selectBox;
	}

	public String getDeleteItem() {
		return deleteItem;
	}

	public void setDeleteItem(String deleteItem) {
		this.deleteItem = deleteItem;
	}

	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getWarMessage() {
		return warMessage;
	}

	public void setWarMessage(String warMessage) {
		this.warMessage = warMessage;
	}
}
