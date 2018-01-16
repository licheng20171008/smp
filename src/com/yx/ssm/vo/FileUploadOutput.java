package com.yx.ssm.vo;

import java.util.List;

import com.yx.ssm.po.DetailnormExt;
import com.yx.ssm.po.ExcelInfo;

public class FileUploadOutput {

	private List<String> messages;
	
	private String message;
	
	private List<DetailnormExt> detailnormExts;
	
	private ExcelInfo excelInfo;
	
	private List<String> filenames;

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public List<DetailnormExt> getDetailnormExts() {
		return detailnormExts;
	}

	public void setDetailnormExts(List<DetailnormExt> detailnormExts) {
		this.detailnormExts = detailnormExts;
	}

	public ExcelInfo getExcelInfo() {
		return excelInfo;
	}

	public void setExcelInfo(ExcelInfo excelInfo) {
		this.excelInfo = excelInfo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getFilenames() {
		return filenames;
	}

	public void setFilenames(List<String> filenames) {
		this.filenames = filenames;
	}
}
