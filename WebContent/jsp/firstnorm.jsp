<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<c:if test="${firstnormOutputVo.commonOutputVo.message != ''}">
    <fieldset>
        <legend>成功信息</legend>
        <span><strong>${firstnormOutputVo.commonOutputVo.message}</strong></span><br />
        </fieldset>
    </c:if>
    <c:if test="${firstnormOutputVo.commonOutputVo.errorMessage != ''}">
    <fieldset>
        <legend>错误信息</legend>
        <span><strong>${firstnormOutputVo.commonOutputVo.errorMessage}</strong></span><br />
        </fieldset>
    </c:if>
    <c:if test="${firstnormOutputVo.commonOutputVo.warMessage != ''}">
    <fieldset>
        <legend>警告信息</legend>
        <span><strong>${firstnormOutputVo.commonOutputVo.warMessage}</strong></span><br />
        </fieldset>
    </c:if>
	<hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
	<form id="firstnormForm" method="post">
		<input type="hidden" id="curPage" name="commonInputVo.curPage" value="${firstnormOutputVo.commonOutputVo.page.curPage }" />
		<div>
			<label for="normname" style="font-family: '宋体'; font-size: 19px;">一级分类：</label>
			<input type="text" id="normname" name="firstnorm.normname" value="${firstnormOutputVo.firstnorm.normname }" />
			<input type="hidden" id="firstnormId" name="firstnorm.id" value="${firstnormOutputVo.firstnorm.id }" />
		</div>
		<hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
		<label for="selectBox" style="font-family: '宋体'; font-size: 19px;">模糊查询：</label>
		<input id="selectBox" name="commonInputVo.selectBox" onclick="onSelectBox();" type="checkbox" value="${firstnormOutputVo.commonOutputVo.selectBox }" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label for="deleteItem" style="font-family: '宋体'; font-size: 19px;">是否删除：</label>
		<input id="deleteItem" name="commonInputVo.deleteItem" onclick="onDeleteItem();" type="checkbox" value="${firstnormOutputVo.commonOutputVo.deleteItem }" />
		<hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
		<input type="button" id="selectBt" onclick="submitForm(0)" value="查询一级分类基本信息" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="insertBt" onclick="submitForm(1)" value="新增一级分类基本信息" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="updateBt" onclick="submitForm(2)" value="修改一级分类基本信息" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="freezeBt" onclick="submitForm(3)" value="冻结/解冻/删除一级分类基本信息" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="exportBt" onclick="submitForm(4)" value="一级分类报表导出" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="initBt" onclick="submitForm(100)" value="返回首页" />
		<c:if test="${firstnormOutputVo.firstnorms!=null}">
			<hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
			<div>
				<div align="right">
					&nbsp;共${firstnormOutputVo.commonOutputVo.page.count }条记录&nbsp;&nbsp;&nbsp;每页${firstnormOutputVo.commonOutputVo.page.perPage }条&nbsp;&nbsp;&nbsp;共${firstnormOutputVo.commonOutputVo.page.countPage }页&nbsp;&nbsp;&nbsp;当前第${firstnormOutputVo.commonOutputVo.page.curPage }页
					<input type="button" value="首页"
						<c:if test="${firstnormOutputVo.commonOutputVo.page.curPage > 1}">onclick="onPage(1);"</c:if> />
					<input type="button" value="上一页"
						<c:if test="${firstnormOutputVo.commonOutputVo.page.curPage > 1}" >onclick="onPage(${firstnormOutputVo.commonOutputVo.page.curPage - 1});"</c:if> />
					<input type="button" value="下一页"
						<c:if test="${firstnormOutputVo.commonOutputVo.page.curPage < firstnormOutputVo.commonOutputVo.page.countPage && firstnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(${firstnormOutputVo.commonOutputVo.page.curPage + 1});"</c:if> />
					<input type="button" value="尾页"
						<c:if test="${firstnormOutputVo.commonOutputVo.page.countPage > 1 && firstnormOutputVo.commonOutputVo.page.curPage < firstnormOutputVo.commonOutputVo.page.countPage}"> onclick="onPage(${firstnormOutputVo.commonOutputVo.page.countPage });" </c:if> />
					<input type="button" value="转到"
						<c:if test="${firstnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(10000);" </c:if> />
					&nbsp;第 <input id="top10000" type="text" style="TEXT-ALIGN: right" size="3" />&nbsp;页
				</div>
				<br />
				<table border="1" cellpadding="10" width="100%" cellspacing="0" style="font-family: '宋体'; font-size: 19px;">
					<tr></tr>
					<tr>
						<th>一级分类ID</th>
						<th>一级分类</th>
						<th>冻结日期</th>
						<th>创建日期</th>
						<th>操作</th>
					</tr>
					<c:forEach var="firstnorm" items="${firstnormOutputVo.firstnorms }" varStatus="status">
						<tr>
							<td id="id_${status.index}">${firstnorm.id }</td>
							<td id="normname_${status.index}">${firstnorm.normname }</td>
							<td><fmt:formatDate value="${firstnorm.abatetime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${firstnorm.createtime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td align="center"><input type="button" onclick="doChange(${status.index});" value="选择" /></td>
						</tr>
					</c:forEach>
				</table>
				<br />
				<div align="right">
					&nbsp;共${firstnormOutputVo.commonOutputVo.page.count }条记录&nbsp;&nbsp;&nbsp;每页${firstnormOutputVo.commonOutputVo.page.perPage }条&nbsp;&nbsp;&nbsp;共${firstnormOutputVo.commonOutputVo.page.countPage }页&nbsp;&nbsp;&nbsp;当前第${firstnormOutputVo.commonOutputVo.page.curPage }页
					<input type="button" value="首页"
						<c:if test="${firstnormOutputVo.commonOutputVo.page.curPage > 1}">onclick="onPage(1);"</c:if> />
					<input type="button" value="上一页"
						<c:if test="${firstnormOutputVo.commonOutputVo.page.curPage > 1}" >onclick="onPage(${firstnormOutputVo.commonOutputVo.page.curPage - 1});"</c:if> />
					<input type="button" value="下一页"
						<c:if test="${firstnormOutputVo.commonOutputVo.page.curPage < firstnormOutputVo.commonOutputVo.page.countPage && firstnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(${firstnormOutputVo.commonOutputVo.page.curPage + 1});"</c:if> />
					<input type="button" value="尾页"
						<c:if test="${firstnormOutputVo.commonOutputVo.page.countPage > 1 && firstnormOutputVo.commonOutputVo.page.curPage < firstnormOutputVo.commonOutputVo.page.countPage}"> onclick="onPage(${firstnormOutputVo.commonOutputVo.page.countPage });" </c:if> />
					<input type="button" value="转到"
						<c:if test="${firstnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(10001);" </c:if> />
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
            if ($("#firstnormId").val() == ''){
                alert("无法对一级分类进行操作，请查询并且选择需要的一级分类信息再进行操作！！");
                return false;
            } else if ($("#normname").val() == ''){
                alert("对一级分类进行操作，请保留一级分类名称！！");
                return false;
            }
        }
        
        if (value != '4'){
            $("#curPage").val(1);
        }
        
        if (value == '0'){
            $("#firstnormForm").attr("action", "firstnormSelect.action");
        } else if (value == '1'){
            if ($("#normname").val() == ''){
                alert("请填写一级分类！！");
                return false;
            } else if ($("#firstnormId").val() != ''){
                alert("此一级分类已存在，无需新增！！");
                return false;
            }
            $("#firstnormForm").attr("action", "firstnormInsert.action");
        } else if (value == '2'){
            $("#firstnormForm").attr("action", "firstnormUpdate.action");
        } else if (value == '3'){
            $("#firstnormForm").attr("action", "firstnormFreeze.action");
        } else if (value == '4'){
            $("#firstnormForm").attr("action", "firstnormReport.action");
        } else if (value == '100') {
            $("#firstnormForm").attr("action", "init.action");
        }
        $("#firstnormForm").submit();
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
            if(value > '${firstnormOutputVo.commonOutputVo.page.countPage }'){
                alert("请输入正确的页数！！");
                return false;
            }
        }
        $("#curPage").val(value);
        $("#firstnormForm").attr("action", "firstnormSelect.action");
        $("#firstnormForm").submit();
    }
    
    function doChange(index){
        $("#normname").val($("#normname_" + index).text());
        $("#firstnormId").val($("#id_" + index).text());
    }
</script>
</html>