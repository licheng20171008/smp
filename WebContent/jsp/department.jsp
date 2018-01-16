<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>武汉亚洲心脏病医院</title>
</head>
<body>
<c:if test="${departmentOutputVo.commonOutputVo.message != ''}">
    <fieldset>
        <legend>成功信息</legend>
        <span><strong>${departmentOutputVo.commonOutputVo.message}</strong></span><br />
        </fieldset>
    </c:if>
    <c:if test="${departmentOutputVo.commonOutputVo.errorMessage != ''}">
    <fieldset>
        <legend>错误信息</legend>
        <span><strong>${departmentOutputVo.commonOutputVo.errorMessage}</strong></span><br />
        </fieldset>
    </c:if>
    <c:if test="${departmentOutputVo.commonOutputVo.warMessage != ''}">
    <fieldset>
        <legend>警告信息</legend>
        <span><strong>${departmentOutputVo.commonOutputVo.warMessage}</strong></span><br />
        </fieldset>
    </c:if>
	<hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
	<form id="departmentForm" method="post">
		<input type="hidden" id="curPage" name="commonInputVo.curPage" value="${departmentOutputVo.commonOutputVo.page.curPage }" />
		<div>
			<label for="department" style="font-family: '宋体'; font-size: 19px;">部门名称：</label>
			<input type="text" id="department" name="department.dpname" value="${departmentOutputVo.department.dpname }" />
			<input type="hidden" id="departmentId" name="department.id" value="${departmentOutputVo.department.id }" />
		</div>
		<hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
		<label for="selectBox">模糊查询：</label>
		<input id="selectBox" name="commonInputVo.selectBox" onclick="onSelectBox();" type="checkbox" value="${departmentOutputVo.commonOutputVo.selectBox }" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label for="deleteItem">是否删除：</label> 
		<input id="deleteItem" name="commonInputVo.deleteItem" onclick="onDeleteItem();" type="checkbox" value="${departmentOutputVo.commonOutputVo.deleteItem }" />
		<hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
		<input type="button" id="selectBt" onclick="submitForm(0)" value="查询部门基本信息" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="insertBt" onclick="submitForm(1)" value="新增部门基本信息" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="updateBt" onclick="submitForm(2)" value="修改部门基本信息" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="freezeBt" onclick="submitForm(3)" value="冻结/解冻/删除部门基本信息" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="exportBt" onclick="submitForm(4)" value="部门报表导出" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="initBt" onclick="submitForm(100)" value="返回首页" />
		<c:if test="${departmentOutputVo.departments!=null}">
			<hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
			<div>
				<div align="right">
					&nbsp;共${departmentOutputVo.commonOutputVo.page.count }条记录&nbsp;&nbsp;&nbsp;每页${departmentOutputVo.commonOutputVo.page.perPage }条&nbsp;&nbsp;&nbsp;共${departmentOutputVo.commonOutputVo.page.countPage }页&nbsp;&nbsp;&nbsp;当前第${departmentOutputVo.commonOutputVo.page.curPage }页
					<input type="button" value="首页"
						<c:if test="${departmentOutputVo.commonOutputVo.page.curPage > 1}">onclick="onPage(1);"</c:if> />
					<input type="button" value="上一页"
						<c:if test="${departmentOutputVo.commonOutputVo.page.curPage > 1}" >onclick="onPage(${departmentOutputVo.commonOutputVo.page.curPage - 1});"</c:if> />
					<input type="button" value="下一页"
						<c:if test="${departmentOutputVo.commonOutputVo.page.curPage < departmentOutputVo.commonOutputVo.page.countPage && departmentOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(${departmentOutputVo.commonOutputVo.page.curPage + 1});"</c:if> />
					<input type="button" value="尾页"
						<c:if test="${departmentOutputVo.commonOutputVo.page.countPage > 1 && departmentOutputVo.commonOutputVo.page.curPage < departmentOutputVo.commonOutputVo.page.countPage}"> onclick="onPage(${departmentOutputVo.commonOutputVo.page.countPage });" </c:if> />
					<input type="button" value="转到"
						<c:if test="${departmentOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(10000);" </c:if> />
					&nbsp;第 <input id="top10000" type="text" style="TEXT-ALIGN: right" size="3" />&nbsp;页
				</div>
				<br />
				<table border="1" cellpadding="10" width="100%" cellspacing="0" style="font-family: '宋体'; font-size: 19px;">
					<tr></tr>
					<tr>
						<th>部门ID</th>
						<th>部门名称</th>
						<th>冻结日期</th>
						<th>创建日期</th>
						<th>操作</th>
					</tr>
					<c:forEach var="department" items="${departmentOutputVo.departments }" varStatus="status">
						<tr>
							<td id="id_${status.index}">${department.id }</td>
							<td id="dpname_${status.index}">${department.dpname }</td>
							<td><fmt:formatDate value="${department.abatetime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${department.createtime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td align="center"><input type="button" onclick="doChange(${status.index});" value="选择" /></td>
						</tr>
					</c:forEach>
				</table>
				<br />
				<div align="right">
                    &nbsp;共${departmentOutputVo.commonOutputVo.page.count }条记录&nbsp;&nbsp;&nbsp;每页${departmentOutputVo.commonOutputVo.page.perPage }条&nbsp;&nbsp;&nbsp;共${departmentOutputVo.commonOutputVo.page.countPage }页&nbsp;&nbsp;&nbsp;当前第${departmentOutputVo.commonOutputVo.page.curPage }页
                    <input type="button" value="首页"
                        <c:if test="${departmentOutputVo.commonOutputVo.page.curPage > 1}">onclick="onPage(1);"</c:if> />
                    <input type="button" value="上一页"
                        <c:if test="${departmentOutputVo.commonOutputVo.page.curPage > 1}" >onclick="onPage(${departmentOutputVo.commonOutputVo.page.curPage - 1});"</c:if> />
                    <input type="button" value="下一页"
                        <c:if test="${departmentOutputVo.commonOutputVo.page.curPage < departmentOutputVo.commonOutputVo.page.countPage && departmentOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(${departmentOutputVo.commonOutputVo.page.curPage + 1});"</c:if> />
                    <input type="button" value="尾页"
                        <c:if test="${departmentOutputVo.commonOutputVo.page.countPage > 1 && departmentOutputVo.commonOutputVo.page.curPage < departmentOutputVo.commonOutputVo.page.countPage}"> onclick="onPage(${departmentOutputVo.commonOutputVo.page.countPage });" </c:if> />
                    <input type="button" value="转到"
                        <c:if test="${departmentOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(10001);" </c:if> />
                    &nbsp;第 <input id="top10001" type="text" style="TEXT-ALIGN: right" size="3" />&nbsp;页
                </div>
			</div>
		</c:if>
	</form>
	<hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
</body>
<script type="text/javascript">
    $(document).ready(function() {
    	var selectBox = $("#selectBox").val();
    	if (selectBox == '1'){
    		$("#selectBox").attr("checked", true);
    	} else {
    		$("#selectBox").attr("checked", false);
    	}
    	var deleteItem = $("#deleteItem").val();
    	if (deleteItem == '1'){
    		$("#deleteItem").attr("checked", true);
    	} else {
    		$("#deleteItem").attr("checked", false);
    	}
    });
    
    function onSelectBox(){
    	var selectBox = $("#selectBox").val();
    	if (selectBox == '1'){
    		$("#selectBox").attr("checked", false);
    		$("#selectBox").prop("value", "0");
    	} else {
    		$("#selectBox").attr("checked", true);
    		$("#selectBox").prop("value", "1");
    	}
    }
    
    function onDeleteItem(){
    	var deleteItem = $("#deleteItem").val();
    	if (deleteItem == '1'){
    		$("#deleteItem").attr("checked", false);
            $("#deleteItem").prop("value", "0");
        } else {
        	$("#deleteItem").attr("checked", true);
            $("#deleteItem").prop("value", "1");
        }
    }
    
    function submitForm(value) {
    	if (value == '2' || value == '3'){
    		if ($("#departmentId").val() == ''){
    			alert("无法对部门进行操作，请查询并且选择需要的部门信息再进行操作！！");
                return false;
    		} else if ($("#department").val() == ''){
    			alert("对部门进行操作，请保留部门名称！！");
                return false;
    		}
    	}
    	
    	if (value != '4'){
    		$("#curPage").val(1);
    	}
    	
    	if (value == '0'){
    		$("#departmentForm").attr("action", "departmentSelect.action");
    	} else if (value == '1'){
    		if ($("#department").val() == ''){
    			alert("请填写部门名称！！");
    			return false;
    		} else if ($("#departmentId").val() != ''){
    			alert("此部门已存在，无需新增！！");
    			return false;
    		}
            $("#departmentForm").attr("action", "departmentInsert.action");
        } else if (value == '2'){
    		$("#departmentForm").attr("action", "departmentUpdate.action");
    	} else if (value == '3'){
    		$("#departmentForm").attr("action", "departmentFreeze.action");
        } else if (value == '4'){
        	$("#departmentForm").attr("action", "departmentReport.action");
        } else if (value == '100') {
        	$("#departmentForm").attr("action", "init.action");
        }
    	$("#departmentForm").submit();
    }
    
    function onPage(value){
    	if (value >= 10000){
    		if (value == '10000'){
    			value = $("#top10000").val();
    		} else {
    			value = $("#top10001").val();
    		}
    		
    		if(!new RegExp("^[1-9]\d*$").test(value)){
    			alert("请输入页数！！");
    			return false;
    		}
    		if(value > '${departmentOutputVo.commonOutputVo.page.countPage }'){
    			alert("请输入正确的页数！！");
    			return false;
    		}
    	}
    	$("#curPage").val(value);
    	$("#departmentForm").attr("action", "departmentSelect.action");
    	$("#departmentForm").submit();
    }
    
    function doChange(index){
    	$("#department").val($("#dpname_" + index).text());
    	$("#departmentId").val($("#id_" + index).text());
    }
</script>
</html>