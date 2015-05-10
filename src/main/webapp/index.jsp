<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>验证码测试</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function codeVerify() {
		var imgCode = $("#imgCode").val();
		var datas = "imgCode=" + imgCode;
		$.ajax({
			type : "POST",
			url : "result/verifyCode",
			data : datas,
			success : function(data) {
				$("#info").html(data);
			},
			error : function(res) {
				console.log(res);
				console.log("请求出错");
			}
		});
	}

	function changeImg() {
		var img = $("#imgid");
		var src = img.attr("src");
		//img.attr("src",src);
		var url = chgUrl(src);
		console.log("url:" + url);
		img.attr("src", url);
	}
	//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳     
	function chgUrl(url) {
		var timestamp = (new Date()).valueOf();
		console.log(timestamp);
		var urls = "";
		if (url.indexOf("?") > 0) {
			urls = url.substring(0, url.indexOf("?"));
			urls = urls + "?timestamp=" + timestamp;
		} else {
			urls = url + "?timestamp=" + timestamp;
		}

		//urlurl = url + "?timestamp=" + timestamp;
		console.log(urls);
		return urls;
	}
</script>
</head>

<body>
	验证码测试<br>
	<br> 验证码：
	<img src="<%=basePath%>servlet/imageRandomServlet" id="imgid" />
	<a href="javascript:changeImg();">换一个</a>
	<br>

	<input type="text" name="imgCode" id="imgCode" />
	<br>
	<input type="button" onclick="javascript:codeVerify();" value="验证" />
	<div id="info"></div>
</body>
</html>
