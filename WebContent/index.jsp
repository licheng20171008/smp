<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.min.js"></script>
<title>武汉亚洲心脏病医院</title>
</head>
<body>
<hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
<form id="initForm" method="post">
<input type="submit" onclick="submitForm(0)" value="部门维护"/>
<br/>
<br/>
<input type="submit" onclick="submitForm(1)" value="一级指标维护"/>
<br/>
<br/>
<input type="submit" onclick="submitForm(2)" value="二级指标维护"/>
<br/>
<br/>
<input type="submit" onclick="submitForm(3)" value="明细指标维护"/>
<br/>
<br/>
<input type="submit" onclick="submitForm(4)" value="批量指标导入"/>
</form>
<hr style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)" width="100%" color="#987cb9" size="3" />
</body>
<script type="text/javascript">
    function submitForm(value) {
    	if (value == '0') {
    		$("#initForm").attr("action", "departmentInit.action");
    	} else if (value == '1') {
            $("#initForm").attr("action", "firstnormInit.action");
        } else if (value == '2') {
    		$("#initForm").attr("action", "secondnormInit.action");
    	} else if (value == '3') {
            $("#initForm").attr("action", "detailnormInit.action");
        } else if (value == '4') {
            $("#initForm").attr("action", "detailnormImport.action");
        } else if (value == '5') {
            $("#initForm").attr("action", "detailnormSelect.action");
        }
    }
</script>
</html>