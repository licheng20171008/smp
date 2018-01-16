package com.yx.ssm.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yx.ssm.service.action.Common;
import com.yx.ssm.service.action.FileHandle;
import com.yx.ssm.service.action.FileReader;
import com.yx.ssm.vo.FileUploadOutput;

@Controller
public class InitController {

	@RequestMapping("/init")
	public ModelAndView init() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/index");
		return modelAndView;
	}
	
	@RequestMapping("/detailnormImport")
	public ModelAndView detailnormImport(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		FileUploadOutput fileUploadOutput = new FileUploadOutput();
		String path = request.getServletContext().getRealPath("filePath");
		fileUploadOutput.setFilenames(Common.getFiles(path));
		fileUploadOutput.setMessages(null);
		modelAndView.addObject("fileUploadOutput", fileUploadOutput);
		modelAndView.setViewName("/jsp/fileUpload");
		return modelAndView;
	}
	
	@RequestMapping("/fileupload")
	public ModelAndView fileupload(HttpServletRequest request) throws IOException, FileUploadException {
		ModelAndView modelAndView = new ModelAndView();
		FileUploadOutput fileUploadOutput = new FileUploadOutput();
		String path = request.getServletContext().getRealPath("filePath");
		fileUploadOutput.setFilenames(Common.getFiles(path));
		new FileReader().execute(request, fileUploadOutput);
		new FileHandle().checkFile(fileUploadOutput);
		new FileHandle().excute(fileUploadOutput);
		modelAndView.addObject("fileUploadOutput", fileUploadOutput);
		modelAndView.setViewName("/jsp/fileUpload");
		return modelAndView;
	}
	
	@RequestMapping("/download")
	public void download(HttpServletRequest request, HttpServletResponse response, 
            @RequestParam("filename") String filename) throws IOException{
		//下载文件路径
		filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
        String path = request.getServletContext().getRealPath("filePath") + File.separator + filename;
        Common.download(path, response);
	}
}