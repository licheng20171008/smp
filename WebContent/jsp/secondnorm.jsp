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
<c:if test="${secondnormOutputVo.commonOutputVo.message != ''}">
    <fieldset>
        <legend>成功信息</legend>
        <span><strong>${secondnormOutputVo.commonOutputVo.message}</strong></span><br />
        </fieldset>
    </c:if>
    <c:if test="${secondnormOutputVo.commonOutputVo.errorMessage != ''}">
    <fieldset>
        <legend>错误信息</legend>
        <span><strong>${secondnormOutputVo.commonOutputVo.errorMessage}</strong></span><br />
        </fieldset>
    </c:if>
    <c:if test="${secondnormOutputVo.commonOutputVo.warMessage != ''}">
    <fieldset>
        <legend>警告信息</legend>
        <span><strong>${secondnormOutputVo.commonOutputVo.warMessage}</strong></span><br />
        </fieldset>
    </c:if>
    <hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
    <form id="secondnormForm" method="post">
    <input type="hidden" id="curPage" name="commonInputVo.curPage" value="${secondnormOutputVo.commonOutputVo.page.curPage }" />
    <div>
            <label for="firstnormSelect" style="font-family: '宋体'; font-size: 19px;">一级分类：</label> 
            <select id="firstnormSelect" name="secondnorm.firstnormid" style="font-family: '宋体'; font-size: 19px;" >
				<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="0">--请选择一级分类--</option>
				<c:forEach var="firstnorm" items="${secondnormOutputVo.firstnorms }">
					<option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="${ firstnorm.id}">${ firstnorm.normname}</option>
				</c:forEach>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label for="normname" style="font-family: '宋体'; font-size: 19px;">二级分类：</label>
			<input type="text" id="normname" name="secondnorm.normname" value="${secondnormOutputVo.secondnorm.normname }"/>
			<input type="hidden" id="secondnormId" name="secondnorm.id" value="${secondnormOutputVo.secondnorm.id }"/>
        </div>
        <hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
        <label for="selectBox" style="font-family: '宋体'; font-size: 19px;">模糊查询：</label>
        <input id="selectBox" name="commonInputVo.selectBox" onclick="onSelectBox();" type="checkbox" value="${secondnormOutputVo.commonOutputVo.selectBox }" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label for="deleteItem" style="font-family: '宋体'; font-size: 19px;">是否删除：</label>
        <input id="deleteItem" name="commonInputVo.deleteItem" onclick="onDeleteItem();" type="checkbox" value="${secondnormOutputVo.commonOutputVo.deleteItem }" />
        <hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
        <input type="button" id="selectBt" onclick="submitForm(0)" value="查询二级分类基本信息" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="insertBt" onclick="submitForm(1)" value="新增二级分类基本信息" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="updateBt" onclick="submitForm(2)" value="修改二级分类基本信息" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="freezeBt" onclick="submitForm(3)" value="冻结/解冻/删除二级分类基本信息" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="exportBt" onclick="submitForm(4)" value="二级分类报表导出" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="initBt" onclick="submitForm(100)" value="返回首页" />
    <c:if test="${secondnormOutputVo.secondnormExts!=null}">
    <hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
            <div >
            <div align="right">
                    &nbsp;共${secondnormOutputVo.commonOutputVo.page.count }条记录&nbsp;&nbsp;&nbsp;每页${secondnormOutputVo.commonOutputVo.page.perPage }条&nbsp;&nbsp;&nbsp;共${secondnormOutputVo.commonOutputVo.page.countPage }页&nbsp;&nbsp;&nbsp;当前第${secondnormOutputVo.commonOutputVo.page.curPage }页
                    <input type="button" value="首页"
                        <c:if test="${secondnormOutputVo.commonOutputVo.page.curPage > 1}">onclick="onPage(1);"</c:if> />
                    <input type="button" value="上一页"
                        <c:if test="${secondnormOutputVo.commonOutputVo.page.curPage > 1}" >onclick="onPage(${secondnormOutputVo.commonOutputVo.page.curPage - 1});"</c:if> />
                    <input type="button" value="下一页"
                        <c:if test="${secondnormOutputVo.commonOutputVo.page.curPage < secondnormOutputVo.commonOutputVo.page.countPage && secondnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(${secondnormOutputVo.commonOutputVo.page.curPage + 1});"</c:if> />
                    <input type="button" value="尾页"
                        <c:if test="${secondnormOutputVo.commonOutputVo.page.countPage > 1 && secondnormOutputVo.commonOutputVo.page.curPage < secondnormOutputVo.commonOutputVo.page.countPage}"> onclick="onPage(${secondnormOutputVo.commonOutputVo.page.countPage });" </c:if> />
                    <input type="button" value="转到"
                        <c:if test="${secondnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(10000);" </c:if> />
                    &nbsp;第 <input id="top10000" type="text" style="TEXT-ALIGN: right" size="3" />&nbsp;页
                </div>
            <br/>
        <table border="1" cellpadding="10" width="100%" cellspacing="0" style="font-family: '宋体'; font-size: 19px;">
        <tr></tr>
        <tr>
            <th>二级分类ID</th>
            <th>二级分类</th>
            <th>所属一级分类</th>
            <th>冻结日期</th>
            <th>创建日期</th>
            <th>操作</th>
        </tr>
            <c:forEach var="secondnormExt" items="${secondnormOutputVo.secondnormExts }" varStatus="status">
                <tr>
                    <td id="id_${status.index}">${secondnormExt.id }</td>
                    <td id="normname_${status.index}">${secondnormExt.normname }</td>
                    <td>${secondnormExt.firstnormName }<input type="hidden"  id="firstnormid_${status.index}" value="${secondnormExt.firstnormid }"/></td>
                    <td><fmt:formatDate value="${secondnorm.abatetime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td><fmt:formatDate value="${secondnorm.createtime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td align="center">
                        <input type="button" onclick="doChange(${status.index});" value="选择" /> 
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <div align="right">
                    &nbsp;共${secondnormOutputVo.commonOutputVo.page.count }条记录&nbsp;&nbsp;&nbsp;每页${secondnormOutputVo.commonOutputVo.page.perPage }条&nbsp;&nbsp;&nbsp;共${secondnormOutputVo.commonOutputVo.page.countPage }页&nbsp;&nbsp;&nbsp;当前第${secondnormOutputVo.commonOutputVo.page.curPage }页
                    <input type="button" value="首页"
                        <c:if test="${secondnormOutputVo.commonOutputVo.page.curPage > 1}">onclick="onPage(1);"</c:if> />
                    <input type="button" value="上一页"
                        <c:if test="${secondnormOutputVo.commonOutputVo.page.curPage > 1}" >onclick="onPage(${secondnormOutputVo.commonOutputVo.page.curPage - 1});"</c:if> />
                    <input type="button" value="下一页"
                        <c:if test="${secondnormOutputVo.commonOutputVo.page.curPage < secondnormOutputVo.commonOutputVo.page.countPage && secondnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(${secondnormOutputVo.commonOutputVo.page.curPage + 1});"</c:if> />
                    <input type="button" value="尾页"
                        <c:if test="${secondnormOutputVo.commonOutputVo.page.countPage > 1 && secondnormOutputVo.commonOutputVo.page.curPage < secondnormOutputVo.commonOutputVo.page.countPage}"> onclick="onPage(${secondnormOutputVo.commonOutputVo.page.countPage });" </c:if> />
                    <input type="button" value="转到"
                        <c:if test="${secondnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(10001);" </c:if> />
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
        $("#firstnormSelect").val("${secondnormOutputVo.secondnorm.firstnormid }");
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
    	if (value == '1' || value == '2'){
    		if ($("#firstnormSelect").val() == '0'){
                alert("请选择一级分类！！");
                return false;
            }
        }
    	
        if (value == '2' || value == '3'){
            if ($("#secondnormId").val() == ''){
                alert("无法对二级分类进行操作，请查询并且选择需要的二级分类信息再进行操作！！");
                return false;
            } else if ($("#normname").val() == ''){
                alert("对二级分类进行操作，请保留二级分类名称！！");
                return false;
            }
        }
        
        if (value != '4'){
            $("#curPage").val(1);
        }
        
        if (value == '0'){
            $("#secondnormForm").attr("action", "secondnormSelect.action");
        } else if (value == '1'){
            if ($("#secondnorm").val() == ''){
                alert("请填写需要新增的二级分类！！");
                return false;
            } else if ($("#secondnormId").val() != ''){
                alert("此二级分类已存在，无需新增！！");
                return false;
            }
            $("#secondnormForm").attr("action", "secondnormInsert.action");
        } else if (value == '2'){
            $("#secondnormForm").attr("action", "secondnormUpdate.action");
        } else if (value == '3'){
            $("#secondnormForm").attr("action", "secondnormFreeze.action");
        } else if (value == '4'){
            $("#secondnormForm").attr("action", "secondnormReport.action");
        } else if (value == '100') {
            $("#secondnormForm").attr("action", "init.action");
        }
        $("#secondnormForm").submit();
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
            if(value > '${secondnormOutputVo.commonOutputVo.page.countPage }'){
                alert("请输入正确的页数！！");
                return false;
            }
        }
        $("#curPage").val(value);
        $("#secondnormForm").attr("action", "secondnormSelect.action");
        $("#secondnormForm").submit();
    }
    
    function doChange(index){
        $("#normname").val($("#normname_" + index).text());
        $("#firstnormSelect").children("option[value=" + $("#firstnormid_" + index).val() + "]").attr("selected", true);
        $("#secondnormId").val($("#id_" + index).text());
    }
</script>
</html>
