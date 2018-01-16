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
<c:if test="${detailnormOutputVo.commonOutputVo.message != ''}">
    <fieldset>
        <legend>成功信息</legend>
        <span><strong>${detailnormOutputVo.commonOutputVo.message}</strong></span><br />
        </fieldset>
    </c:if>
    <c:if test="${detailnormOutputVo.commonOutputVo.errorMessage != ''}">
    <fieldset>
        <legend>错误信息</legend>
        <span><strong>${detailnormOutputVo.commonOutputVo.errorMessage}</strong></span><br />
        </fieldset>
    </c:if>
    <c:if test="${detailnormOutputVo.commonOutputVo.warMessage != ''}">
    <fieldset>
        <legend>警告信息</legend>
        <span><strong>${detailnormOutputVo.commonOutputVo.warMessage}</strong></span><br />
        </fieldset>
    </c:if>
    <hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
    <form id="detailnormForm" method="post">
    <input type="hidden" id="curPage" name="commonInputVo.curPage" value="${detailnormOutputVo.commonOutputVo.page.curPage }" />
        <div>
            <table border="0" cellpadding="10" width="100%" cellspacing="0" style="font-family: '宋体'; font-size: 19px;">
                <tr>
                    <td><label for="firstnormSelect" style="font-family: '宋体'; font-size: 19px;">一级指标名称：</label></td>
                    <td>
                        <select id="firstnormSelect" name="detailnorm.firstnormid" style="font-family: '宋体'; font-size: 19px;" onchange="submitForm(0);">
                            <option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="0">--请选择一级指标--</option>
                            <c:forEach var="firstnorm" items="${detailnormOutputVo.firstnorms }">
                                <option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="${ firstnorm.id}">${ firstnorm.normname}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><label for="secondnormSelect" style="font-family: '宋体'; font-size: 19px;">二级指标名称：</label></td>
                    <td>
                        <select id="secondnormSelect" name="detailnorm.secondnormid" style="font-family: '宋体'; font-size: 19px;">
                            <option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="0">--请选择二级指标--</option>
                            <c:forEach var="secondnorm" items="${detailnormOutputVo.secondnorms }">
                                <option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="${ secondnorm.id}">${ secondnorm.normname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="normid" style="font-family: '宋体'; font-size: 19px;">指标编号：</label></td>
                    <td><input type="text" id="normid" name="detailnorm.normid" value="${detailnormOutputVo.detailnorm.normid }" /></td>
                    <td><label for="normname" style="font-family: '宋体'; font-size: 19px;">指标名称：</label></td>
                    <td><input type="text" id="normname" name="detailnorm.normname" value="${detailnormOutputVo.detailnorm.normname }" /></td>
                    <td><input type="hidden" id="detailnormId" name="detailnorm.id" value="${detailnormOutputVo.detailnorm.id }"/></td>
                </tr>
                <tr>
                    <td><label for="normdescription" style="font-family: '宋体'; font-size: 19px;">指标定义：</label></td>
                    <td><textarea id="normdescription" name="detailnorm.normdescription">${detailnormOutputVo.detailnorm.normdescription }</textarea></td>
                    <td><label for="normformula" style="font-family: '宋体'; font-size: 19px;">计算公式：</label></td>
                    <td><textarea id="normformula" name="detailnorm.normformula">${detailnormOutputVo.detailnorm.normformula }</textarea></td>
                </tr>
                <tr>
                    <td><label for="computingcycle" style="font-family: '宋体'; font-size: 19px;">统计周期：</label></td>
                    <td><input type="text" id="computingcycle" name="detailnorm.computingcycle" value="${detailnormOutputVo.detailnorm.computingcycle }" /></td>
                    <td><label for="cycleunit" style="font-family: '宋体'; font-size: 19px;">周期单位：</label></td>
                    <td><input type="text" id="cycleunit" name="detailnorm.cycleunit" value="${detailnormOutputVo.detailnorm.cycleunit }" /></td>
                </tr>
                <tr>
                    <td><label for="departmentSelect" style="font-family: '宋体'; font-size: 19px;">所属部门：</label></td>
                    <td>
                        <select id="departmentSelect" name="detailnorm.departmentid" style="font-family: '宋体'; font-size: 19px;">
                            <option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="0">--请选择部门--</option>
                            <c:forEach var="department" items="${detailnormOutputVo.departments }">
                                <option style="width: 400px; font-family: '宋体'; font-size: 19px;" value="${ department.id}">${ department.dpname}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><label for="remark" style="font-family: '宋体'; font-size: 19px;">备注：</label></td>
                    <td><textarea id="remark" name="detailnorm.remark">${detailnormOutputVo.detailnorm.remark }</textarea></td>
                </tr>
            </table>
        </div>
        <hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
        <span>模糊查询：</span>
        <input id="selectBox" name="commonInputVo.selectBox" onclick="onSelectBox();" type="checkbox" value="${detailnormOutputVo.commonOutputVo.selectBox }" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span>是否删除：</span>
        <input id="deleteItem" name="commonInputVo.deleteItem" onclick="onDeleteItem();" type="checkbox" value="${detailnormOutputVo.commonOutputVo.deleteItem }" />
        <hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
        <input type="button" id="selectBt" onclick="submitForm(0)" value="查询详细指标基本信息" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="insertBt" onclick="submitForm(1);" value="新增详细指标基本信息" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="updateBt" onclick="submitForm(2);" value="修改详细指标基本信息" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="freezeBt" onclick="submitForm(3);" value="冻结/解冻/删除详细指标基本信息" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="exportBt" onclick="submitForm(4);" value="详细指标报表导出" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="deleteBt" onclick="deleteValue();" value="一键清空查询条件" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="initBt" onclick="submitForm(100);" value="返回首页" />
        <c:if test="${detailnormOutputVo.detailnormExts != null}">
    <hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
            <div>
            <div align="right">
                    &nbsp;共${detailnormOutputVo.commonOutputVo.page.count }条记录&nbsp;&nbsp;&nbsp;每页${detailnormOutputVo.commonOutputVo.page.perPage }条&nbsp;&nbsp;&nbsp;共${detailnormOutputVo.commonOutputVo.page.countPage }页&nbsp;&nbsp;&nbsp;当前第${detailnormOutputVo.commonOutputVo.page.curPage }页
                    <input type="button" value="首页"
                        <c:if test="${detailnormOutputVo.commonOutputVo.page.curPage > 1}">onclick="onPage(1);"</c:if> />
                    <input type="button" value="上一页"
                        <c:if test="${detailnormOutputVo.commonOutputVo.page.curPage > 1}" >onclick="onPage(${detailnormOutputVo.commonOutputVo.page.curPage - 1});"</c:if> />
                    <input type="button" value="下一页"
                        <c:if test="${detailnormOutputVo.commonOutputVo.page.curPage < detailnormOutputVo.commonOutputVo.page.countPage && detailnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(${detailnormOutputVo.commonOutputVo.page.curPage + 1});"</c:if> />
                    <input type="button" value="尾页"
                        <c:if test="${detailnormOutputVo.commonOutputVo.page.countPage > 1 && detailnormOutputVo.commonOutputVo.page.curPage < detailnormOutputVo.commonOutputVo.page.countPage}"> onclick="onPage(${detailnormOutputVo.commonOutputVo.page.countPage });" </c:if> />
                    <input type="button" value="转到"
                        <c:if test="${detailnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(10000);" </c:if> />
                    &nbsp;第 <input id="top10000" type="text" style="TEXT-ALIGN: right" size="3" />&nbsp;页
                </div>
            <br/>
        <table border="1" cellpadding="10" width="100%" cellspacing="0" style="font-family: '宋体'; font-size: 19px;">
        <tr></tr>
        <tr>
            <th>指标ID</th>
            <th>一级分类</th>
            <th>二级分类</th>
            <th>指标编号</th>
            <th>指标名称</th>
            <th>指标定义</th>
            <th>计算公式</th>
            <th>统计周期</th>
            <th>周期单位</th>
            <th>部门</th>
            <th>备注</th>
            <th>冻结日期</th>
            <th>创建日期</th>
            <th>操作</th>
        </tr>
            <c:forEach var="detailnormExt" items="${detailnormOutputVo.detailnormExts }" varStatus="status">
                <tr>
                    <td id="id_${status.index}">${detailnormExt.id }</td>
                    <td>${detailnormExt.firstnormName }<input id="firstnormid_${status.index}" type="hidden" value="${detailnormExt.firstnormid }"/></td>
                    <td>${detailnormExt.secondnormName }<input id="secondnormid_${status.index}" type="hidden" value="${detailnormExt.secondnormid }"/></td>
                    <td id="normid_${status.index}">${detailnormExt.normid }</td>
                    <td id="normname_${status.index}">${detailnormExt.normname }</td>
                    <td id="normdescription_${status.index}">${detailnormExt.normdescription }</td>
                    <td id="normformula_${status.index}">${detailnormExt.normformula }</td>
                    <td id="computingcycle_${status.index}">${detailnormExt.computingcycle }</td>
                    <td id="cycleunit_${status.index}">${detailnormExt.cycleunit }</td>
                    <td>${detailnormExt.dpName }<input id="departmentid_${status.index}" type="hidden" value="${detailnormExt.departmentid }"/></td>
                    <td id="remark_${status.index}">${detailnormExt.remark }</td>
                    <td><fmt:formatDate value="${detailnormExt.abatetime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td><fmt:formatDate value="${detailnormExt.createtime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td align="center">
                        <input type="button" onclick="doChange(${status.index});" value="选择" /> 
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <div align="right">
                    &nbsp;共${detailnormOutputVo.commonOutputVo.page.count }条记录&nbsp;&nbsp;&nbsp;每页${detailnormOutputVo.commonOutputVo.page.perPage }条&nbsp;&nbsp;&nbsp;共${detailnormOutputVo.commonOutputVo.page.countPage }页&nbsp;&nbsp;&nbsp;当前第${detailnormOutputVo.commonOutputVo.page.curPage }页
                    <input type="button" value="首页"
                        <c:if test="${detailnormOutputVo.commonOutputVo.page.curPage > 1}">onclick="onPage(1);"</c:if> />
                    <input type="button" value="上一页"
                        <c:if test="${detailnormOutputVo.commonOutputVo.page.curPage > 1}" >onclick="onPage(${detailnormOutputVo.commonOutputVo.page.curPage - 1});"</c:if> />
                    <input type="button" value="下一页"
                        <c:if test="${detailnormOutputVo.commonOutputVo.page.curPage < detailnormOutputVo.commonOutputVo.page.countPage && detailnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(${detailnormOutputVo.commonOutputVo.page.curPage + 1});"</c:if> />
                    <input type="button" value="尾页"
                        <c:if test="${detailnormOutputVo.commonOutputVo.page.countPage > 1 && detailnormOutputVo.commonOutputVo.page.curPage < detailnormOutputVo.commonOutputVo.page.countPage}"> onclick="onPage(${detailnormOutputVo.commonOutputVo.page.countPage });" </c:if> />
                    <input type="button" value="转到"
                        <c:if test="${detailnormOutputVo.commonOutputVo.page.countPage > 1}"> onclick="onPage(10001);" </c:if> />
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
        $("#firstnormSelect").val("${detailnormOutputVo.detailnorm.firstnormid }");
        $("#secondnormSelect").val("${detailnormOutputVo.detailnorm.secondnormid }");
        $("#departmentSelect").val("${detailnormOutputVo.detailnorm.departmentid }");
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
            if ($("#secondnormSelect").val() == '0'){
                alert("请选择二级分类！！");
                return false;
            }
        }
        if (value == '2' || value == '3'){
            if ($("#detailnormId").val() == ''){
                alert("无法对指标进行操作，请查询并且选择需要的指标信息再进行操作！！");
                return false;
            } else if ($("#normname").val() == ''){
                alert("对指标进行操作，请保留指标名称！！");
                return false;
            }
        }
        
        if (value != '4'){
            $("#curPage").val(1);
        }
        
        if (value == '0'){
            $("#detailnormForm").attr("action", "detailnormSelect.action");
        } else if (value == '1'){
            if ($("#normname").val() == ''){
                alert("请填写指标名称！！");
                return false;
            } else if ($("#detailnormId").val() != ''){
                alert("此指标已存在，无需新增！！");
                return false;
            }
            $("#detailnormForm").attr("action", "detailnormInsert.action");
        } else if (value == '2'){
            $("#detailnormForm").attr("action", "detailnormUpdate.action");
        } else if (value == '3'){
            $("#detailnormForm").attr("action", "detailnormFreeze.action");
        } else if (value == '4'){
            $("#detailnormForm").attr("action", "detailnormReport.action");
        } else if (value == '100') {
            $("#detailnormForm").attr("action", "init.action");
        }
        $("#detailnormForm").submit();
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
        $("#detailnormForm").attr("action", "detailnormSelect.action");
        $("#detailnormForm").submit();
    }
    
    function doChange(index){
        $("#remark").text($("#remark_" + index).text());
        $("#normid").val($("#normid_" + index).text());
        $("#detailnormId").val($("#id_" + index).text());
        $("#cycleunit").val($("#cycleunit_" + index).text());
        $("#normname").val($("#normname_" + index).text());
        $("#normformula").text($("#normformula_" + index).text());
        $("#computingcycle").val($("#computingcycle_" + index).text());
        $("#normdescription").text($("#normdescription_" + index).text());
        $("#firstnormSelect").children("option[value=" + $("#firstnormid_" + index).val() + "]").attr("selected", true);
        $("#departmentSelect").children("option[value=" + $("#departmentid_" + index).val() + "]").attr("selected", true);
        $("#secondnormSelect").children("option[value=" + $("#secondnormid_" + index).val() + "]").attr("selected", true);
    }
    
    function deleteValue(){
        $("#remark").text("");
        $("#normid").val("");
        $("#detailnormId").val(0);
        $("#cycleunit").val("");
        $("#normname").val("");
        $("#normformula").text("");
        $("#computingcycle").val("");
        $("#normdescription").text("");
        $("#firstnormSelect").children("option[value=0]").attr("selected", true);
        $("#departmentSelect").children("option[value=0]").attr("selected", true);
        $("#secondnormSelect").children("option[value=0]").attr("selected", true);
    }
</script>
</html>
