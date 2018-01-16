<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>武汉亚洲心脏病医院</title>
</head>
<body>
    <c:if test="${fileUploadOutput.messages != null}">
    <fieldset>
        <legend>错误信息</legend>
        <c:forEach var="errorMessage" items="${fileUploadOutput.messages }">
            <span><strong>${errorMessage}</strong></span><br />
        </c:forEach>
        </fieldset>
    </c:if>
    <c:if test="${fileUploadOutput.message != null}">
    <fieldset>
        <legend>成功信息</legend>
        <span><strong>${fileUploadOutput.message}</strong></span><br />
        </fieldset>
    </c:if>
	<hr style="FILTER: alpha(opacity = 1, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
	<form id="fileuploadForm" method="post" enctype="multipart/form-data">
		<div>
			<span><strong>文件路径选择：</strong></span><input id="filePath" type="file" name="file" />
		</div>
		<br />
		<div>
			<input type="button" value="文件上传" onclick="submitForm(0)" /> 
			<input type="button" value="返回首页" onclick="submitForm(100)" />
		</div>
		<hr style="FILTER: alpha(opacity = 1, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
    <fieldset>
        <legend>模板下载</legend>
        <c:forEach var="filename" items="${fileUploadOutput.filenames }">
            <a href="download.action?filename=${filename}" onclick="encode(this);"><strong>${filename}</strong></a><br />
        </c:forEach>
        </fieldset>
	</form>
        <hr style="FILTER: alpha(opacity = 1, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
</body>
<script type="text/javascript">
function submitForm(value) {
    if (value == '0'){
        $("#fileuploadForm").attr("action", "fileupload.action");
    } else if (value == '100') {
        $("#fileuploadForm").attr("action", "init.action");
    }
    $("#fileuploadForm").submit();
}

function encode(obj){
	var href = $(obj).attr("href");
	href = encodeURI(href);
	$(obj).attr("href", href);
}
</script>
</html>