<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>注册</title>
  <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
  <meta name="author" content="Vincent Garreau" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/css/style.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css"/>
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/showBo.css" />
</head>
<body>

<div id="particles-js">
			<%
			String path=request.getContextPath();
			HttpSession ses= request.getSession();
			%>
	<form action="<%=path %>/RegisterServlet" method="post" onsubmit="return check()">
		<div class="login">
			<div class="login-top">
				注册
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img"><img src="${pageContext.request.contextPath}/img/name.png"/></div>
				<div class="login-center-input">
					<input type="text" id="username" name="username" required="required" value="" placeholder="请输入您的用户名" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的用户名'"/>
					<div class="login-center-input-text">用户名</div>
				</div>
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img"><img src="${pageContext.request.contextPath}/img/password.png"/></div>
				<div class="login-center-input">
					<input type="password" id="password" name="password" required="required" value="" placeholder="请输入您的密码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'"/>
					<div class="login-center-input-text">密码</div>
				</div>
			</div>
			<div class="login-center clearfix">
				<div class="login-center-img"><img src="${pageContext.request.contextPath}/img/password.png"/></div>
				<div class="login-center-input">
					<input type="password" id="password2" name="password2" required="required" value="" placeholder="请确认您的密码" onfocus="this.placeholder=''" onblur="this.placeholder='请确认您的密码'"/>
					<div class="login-center-input-text">确认密码</div>
				</div>
			</div>
			<div style="text-align:center;margin-top:-90px;">
				<input type="submit"  value="注册" class="login-button" />
			</div>
			<div style="text-align:center;margin-top:-30px;">	
				<input type="button" class="login-button" onclick="{location.href='login.jsp'}"value="返回登录" />
			</div>
		</div>
		<div class="sk-rotating-plane"></div>
	</form>
</div>

<!-- scripts -->
<script src="${pageContext.request.contextPath}/js/particles.min.js"></script>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/show.js"></script>
<script type="text/javascript">
	function hasClass(elem, cls) {
	  cls = cls || '';
	  if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
	  return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
	}
	 
	function addClass(ele, cls) {
	  if (!hasClass(ele, cls)) {
	    ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
	  }
	}
	 
	function removeClass(ele, cls) {
	  if (hasClass(ele, cls)) {
	    var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
	    while (newClass.indexOf(' ' + cls + ' ') >= 0) {
	      newClass = newClass.replace(' ' + cls + ' ', ' ');
	    }
	    ele.className = newClass.replace(/^\s+|\s+$/g, '');
	  }
	}
	function check(){ 
		var pwd1 = document.getElementById("password").value;
	    var pwd2 = document.getElementById("password2").value;
		if(pwd1!=pwd2){ 
			Showbo.Msg.alert('输入密码不一致!');
			return false;
			} 
		} 	
	var register = '<%= ses.getAttribute("register")%>';
	  if(register=='no'){
		  Showbo.Msg.alert("用户已存在，请重新注册！");
		  <% ses.removeAttribute("register");%>
	  }
</script>
</body>
</html>