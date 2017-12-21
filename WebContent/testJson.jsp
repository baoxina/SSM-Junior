<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>json交互测试</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	//请求json串，返回json对象
	function requestJson() {
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/testJson/requestJson.action",
			//此处要注意修改contentType为 application/json
			contentType:"application/json;charset=utf-8",
			//请求的数据是json串，商品信息
			data:'{"name":"手机","price":999}',
			success:function(rtnResult){//rtnResult是返回的json对象
				alert(rtnResult.name);
			}
		});
	}
	//请求key/value串，返回json对象
	function responseJson() {
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/testJson/responseJson.action",
			//发送K-V串时，无需指定contentType，使用默认的application/x-www-form-urlencoded;charset=utf-8
			data:{"name":"手机","price":999},
			success:function(rtnResult){//rtnResult是返回的json对象
				alert(rtnResult.price);
			}
		});
	}
</script>
</head>
<body>
	<input type="button" value="输入json串，输出json串" onclick="requestJson()"/>
	<input type="button" value="输入key/value串，输出json串" onclick="responseJson()"/>
</body>
</html>