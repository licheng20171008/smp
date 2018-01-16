package com.yx.ssm.vo;

public class CommonInputVo {

    private String selectBox;
	
	private String deleteItem;
	
	private int curPage;

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

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
}
