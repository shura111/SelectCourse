<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>修改密码--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../../css/user.css" media="all" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/showBo.css" />
</head>
<%  HttpSession ses = request.getSession();	
String path=request.getContextPath();
%>
<body class="childrenBody">
	<form class="layui-form changePwd" action="<%=path %>/ChangepwdServlet" method="post" onsubmit="return check()">
		<div style="margin:0 0 15px 110px;color:#f00;">新密码必须两次输入一致才能提交</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">用户名</label>
		    <div class="layui-input-block">
		    	<input type="text" name="username" value="${SesUser.getUser_name() }" disabled class="layui-input layui-disabled">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">用户身份</label>
		    <div class="layui-input-block">
		    	<input type="text" name="usertype" value="${SesUser.getUser_type() }" disabled class="layui-input layui-disabled">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">旧密码</label>
		    <div class="layui-input-block">
		    	<input type="password" id="oldpassword" name="oldpassword" value="" placeholder="请输入旧密码" lay-verify="required|oldPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">新密码</label>
		    <div class="layui-input-block">
		    	<input type="password" id="newpassword" name="newpassword" value="" placeholder="请输入新密码" lay-verify="required|newPwd" id="oldPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">确认密码</label>
		    <div class="layui-input-block">
		    	<input type="password" id="newpassword2" name="newpassword2" value="" placeholder="请确认密码" lay-verify="required|confirmPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="">
		    <div class="layui-input-block">
		    	<button type="submit"  class="layui-btn" >立即修改</button>
				<button type="reset" class="layui-btn layui-btn-warm layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script type="text/javascript" src="address.js"></script>
	<script type="text/javascript" src="user.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/show.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="../../layui/lay/dest/layui.all.js"></script>
	<script type="text/javascript">
	var layer = layui.layer
	 ,form = layui.form();
	
	function check(){ 
		var password=${SesUser.getUser_password() }
		var pwd=document.getElementById("oldpassword").value;
		var pwd1 = document.getElementById("newpassword").value;
	    var pwd2 = document.getElementById("newpassword2").value;
	    if(pwd == '' || pwd == undefined || pwd == null)
	    {
	    	layer.alert('请输入密码!');
			return false;
	    }
	    if(pwd1 == '' || pwd1 == undefined || pwd1 == null)
	    {
	    	layer.alert('请输入新密码!');
			return false;
	    }
	    if(pwd2 == '' || pwd2 == undefined || pwd2 == null)
	    {
	    	layer.alert('请输入确认密码!');
			return false;
	    }
	    if(pwd!=password)
		{
	    	layer.alert('密码输入错误!');
			return false;
		}
		if(pwd1!=pwd2){ 
			layer.alert('两次输入密码不一致!');
			return false;
			} 
		} 	
	var pwd = '<%= ses.getAttribute("pwd")%>';
	  if(pwd=='suc'){
		  layer.msg("修改成功！2s后跳转登录页面");
		  setTimeout(function(){
			 
			  parent.location.href="../login/login.jsp";
			},2000);
		  //window.parent.location.href="../login/login.jsp"
		  <% ses.removeAttribute("pwd");%>
	  }
	  else if(pwd=="fail")
		  {
		  layer.alert("修改失败！");
		  <% ses.removeAttribute("pwd");%>
		  }
	
	</script>
</body>
</html>