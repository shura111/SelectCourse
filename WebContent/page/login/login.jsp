<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>登录</title>
  <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
  <meta name="author" content="Vincent Garreau" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <link rel="stylesheet" media="screen" href="../../css/style.css">
  <link rel="stylesheet" type="text/css" href="../../css/reset.css"/>
   <link type="text/css" rel="stylesheet" href="../../css/showBo.css" />
   <link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
</head>
<body>

<div id="particles-js">
	
		<div class="login">
			<div class="login-top">
				登录
			</div>
			<%
				String path=request.getContextPath();
			%>
			<form action="<%=path %>/LoginServlet" method="post">
			
				<div class="login-center clearfix">
					<div class="login-center-img"><img src="../../img/name.png"/></div>
					<div class="login-center-input">
						<input type="text" id="userid" name="userid" required="required" value="" placeholder="请输入您的用户账号" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的用户账号'"/>
						<div class="login-center-input-text">账号</div>
					</div>
				</div>
				
				<div class="login-center clearfix">
					<div class="login-center-img"><img src="../../img/password.png"/></div>
					<div class="login-center-input">
						<input type="password" id="password" required="required" name="password" value="" placeholder="请输入您的密码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'"/>
						<div class="login-center-input-text">密码</div>
					</div>
				</div>
				<div style="width:90% ;margin:auto">
					<div style=" width:20%; float:left;margin-left:10%; text-align: center">
						管理员<input type="radio" name="user_type" value="管理员">
					</div>
					<div style="width:20%; float:left ;text-align: center">
						教师<input type="radio" name="user_type" value="教师" checked="checked">
					</div>
					<div style="width:20%; float:left;;text-align: center">
						学生<input type="radio" name="user_type" value="学生">
					</div>
				
				</div>
				
				<div style="text-align:center;">
					<input type="submit"  class="login-button" value="登录"/>
				</div>
				
			</form>
		</div>
		<div class="sk-rotating-plane"></div>
	
</div>

<!-- scripts -->
<script src="../../js/particles.min.js"></script>
<script src="../../js/app.js"></script>
<script type="text/javascript" src="../../js/show.js"></script>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="../../layui/lay/dest/layui.all.js"></script>
<script type="text/javascript">
var layer = layui.layer
 ,form = layui.form();
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
	 
	<%  HttpSession ses = request.getSession();	 %>
	var register = '<%= ses.getAttribute("register")%>';
  var errori ='<%=ses.getAttribute("error")%>';
  var pwd='<%=ses.getAttribute("pwd")%>';
  var Filter='<%=ses.getAttribute("Filter")%>';
  var sessiondestory='<%=ses.getAttribute("sessiondestory")%>';
  if(errori=='yes'){
	  layer.alert("登录失败，请重新登录!");
	  <% ses.removeAttribute("error");%>
  }
  if(register=='suc'){
	  layer.alert("注册成功，请登录!");
	  <% ses.removeAttribute("register");%>;
  }
  if(pwd=='suc'){
	  layer.alert("密码修改成功，请登录!");
	  <% ses.removeAttribute("pwd");%>;
  }
  if(Filter=='yes'){
	  layer.alert("您还未登录，请先登录！");
	  <% ses.removeAttribute("Filter");%>;
  }
  if(sessiondestory=='yes')
	  {
	  layer.alert("您离开时间过长，请重新登录");
	  <% ses.removeAttribute("sessiondestory");%>;
	  }
</script>
</body>
</html>
