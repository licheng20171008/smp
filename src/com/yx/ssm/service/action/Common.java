package com.yx.ssm.service.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;

/**
 * 公用类
 */
public class Common {

	/**
	 * 系统时间根据格式类型返回
	 * 
	 * @param dateFormat 时间格式
	 * @return 字符串时间
	 */
	public static String sysDate(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}

	/**
	 * 系统时间根据格式类型返回
	 * 
	 * @param dateFormat 时间格式
	 * @param date 字符串日期
	 * @return 字符串时间
	 * @throws ParseException 
	 */
	public static Date stringToDate(String dateFormat, String date) throws Exception {
		return new SimpleDateFormat(dateFormat).parse(date);
	}
	
	/**
	 * 字符串类型的数字前面添加0
	 * 
	 * @param param 字符串
	 * @param length 字符串加0前的可能长度
	 * @param size 字符串加0后的总长度
	 * @return 加0后的字符串
	 */
	public static String addZero(String param, int length, int size) {
		return param.length() < length ? String.format("%0" + size + "d", Integer.parseInt(param)) : param;
	}

	/**
	 * 计算两个时间的天数差
	 * 
	 * @param date1 开始时间
	 * @param date2 结束时间
	 * @return 天数
	 */
	public static int differentDaysByMillisecond(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}

	/**
	 * 获得指定日期的前后天数
	 * 
	 * @param day 指定日期
	 * @param days 正数表示：后N天，负数表示：前N天
	 * @return 取得的日期
	 */
	public static Date getDayBefore(Date day, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		int dateDay = c.get(Calendar.DATE);
		c.set(Calendar.DATE, dateDay + days);
		return c.getTime();
	}

	/*
	 * 文件下载
	 * 
	 * @param path 文件地址
	 * 
	 * @param response
	 */
	public static void download(String path, HttpServletResponse response) throws IOException {

		// path是指欲下载的文件的路径。
		File file = new File(path);
		// 取得文件名。
		String filename = file.getName();
		// 以流的形式下载文件。
		InputStream fis = new BufferedInputStream(new FileInputStream(file));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO-8859-1"));
		response.addHeader("Content-Length", "" + file.length());
		response.setContentType("application/vnd.ms-excel");
		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		toClient.write(buffer);
		toClient.flush();
		toClient.close();
	}

	/**
	 * 
	 * 初始化bean的元素后返回初始化对象
	 * 
	 * @param clazz bean的JAVA类
	 * @return 初始化的bean对象
	 */
	public static Object InitBean(Class<?> clazz) {

		Field[] fs = clazz.getDeclaredFields();
		Method[] me = clazz.getMethods();
		Object cla = null;
		try {
			cla = clazz.newInstance();
			for (Field field : fs) {
				field.setAccessible(true);
				String name = field.getName();
				String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
				Method method = null;
				for (Method m : me) {
					if (("set" + methodName).equals(m.getName())) {
						method = m;
						break;
					}
				}
				if ("String".equals(field.getType().getSimpleName())) {
					method.invoke(cla, "");
				} else if ("int".equals(field.getType().getName())) {
					method.invoke(cla, 0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cla;
	}
	
	/**
	 * 
	 * 流水号自动加一
	 * 
	 * 例如：0001自动加一变成0002
	 * 
	 * @param code 流水号参数
	 * @return 计算后的流水号
	 */
	public static String queue(String code) {
		Integer intQueue = Integer.parseInt(code);
		intQueue++;
		DecimalFormat dcmf = new DecimalFormat(code);
		return dcmf.format(intQueue);
	}
	
	/**
	 * 
	 * 获取文件夹下面的子文件
	 * 
	 * @param path 文件夹全路径
	 * @return 所有的子文件名
	 */
	public static List<String> getFiles(String path) {
		File file = new File(path);
		List<String> lists = new ArrayList<String>();
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File fileCH : files) {
				if (fileCH.isFile()) {
					lists.add(fileCH.getName());
				}
			}
		}
		return lists;
	}
	
	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param commonList
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)等
	 * @param docsPath 临时文件储存位置
	 * @param itemArray 标题栏名称以及字段名
	 * @param fileTitle 表格标题名
	 * @throws FileNotFoundException 
	 * @throws Exception 
	 */
	public static void exportExcel(List<?> commonList, String docsPath, String[][] itemArray, String fileTitle) throws Exception {
		
		// 声明一个工作薄
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(fileTitle);
		
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 20);
		
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		// 把字体应用到当前的样式
		style.setFont(font);
		
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < itemArray[0].length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(itemArray[0][i]);
			cell.setCellValue(text);
		}
		
		// 遍历集合数据，产生数据行
		for (int j = 0; j < commonList.size(); j++) {
			
			// 创建数据行数
			HSSFRow rowData = sheet.createRow(j + 1);
			
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			for (int i = 0; i < itemArray[1].length; i++) {
				HSSFCell cell = rowData.createCell(i);
				cell.setCellStyle(style2);
				String fieldName = itemArray[1][i];
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

				@SuppressWarnings("unchecked")
				Class<? extends T> tCls = (Class<? extends T>) commonList.get(j).getClass();
				Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
				Object value = getMethod.invoke(commonList.get(j), new Object[] {});

				// 判断值的类型后进行强制类型转换
				HSSFRichTextString textValue = null;
				if (value instanceof Integer) {
					int intValue = (Integer) value;
					cell.setCellValue(intValue);
				} else if (value instanceof Float) {
					float fValue = (Float) value;
					textValue = new HSSFRichTextString(String.valueOf(fValue));
					cell.setCellValue(textValue);
				} else if (value instanceof Double) {
					double dValue = (Double) value;
					textValue = new HSSFRichTextString(String.valueOf(dValue));
					cell.setCellValue(textValue);
				} else if (value instanceof Long) {
					long longValue = (Long) value;
					cell.setCellValue(longValue);
				} else if (value instanceof Date) {
					Date date = (Date) value;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					textValue = new HSSFRichTextString(sdf.format(date));
				} else if (value != null) {
					// 其它数据类型都当作字符串简单处理
					textValue = new HSSFRichTextString(value.toString());
				}

				// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
				if (textValue != null) {
					Pattern p = Pattern.compile("^//d+(//.//d+)?$");
					Matcher matcher = p.matcher(textValue.toString());
					if (matcher.matches()) {
						// 是数字当作double处理
						cell.setCellValue(Double.parseDouble(textValue.toString()));
					} else {
						HSSFRichTextString richString = new HSSFRichTextString(textValue.toString());
						HSSFFont font3 = workbook.createFont();
						font3.setColor(HSSFColor.BLUE.index);
						richString.applyFont(font3);
						cell.setCellValue(richString);
					}
				}
			}
		}
		
		// 文件流
		OutputStream os = new FileOutputStream(docsPath);
		workbook.write(os);
		os.close();
	}
}