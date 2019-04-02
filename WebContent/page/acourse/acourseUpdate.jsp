<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.ceh.bean.Course"%>
<%@page import="cn.ceh.dao.Coursedao" %>
<%@page language="java" import="java.util.*,java.awt.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>申请课程更新--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
     <link type="text/css" rel="stylesheet" href="../../css/showBo.css" />
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/show.js"></script>
    
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="../../css/font.css">
    <link rel="stylesheet" href="../../css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/xadmin.js"></script>
</head>
<body class="childrenBody">
			<%
						String path=request.getContextPath();
						HttpSession ses = request.getSession();
						String id=request.getParameter("aid");
						Coursedao dao=new Coursedao(); 
						Course c=dao.find(id);
			%>
	<form class="layui-form" action="<%=path %>/UpdateServlet" method="post" onSubmit="return check();" >
	<input type="hidden" name="id" value="<%=c.course_id%>">
	<input type="hidden" name="time" value="<%=c.course_time%>">
	<input type="hidden" name="place" value="<%=c.course_place%>">
	<input type="hidden" name="term" value="<%=c.course_term%>">
	<input type="hidden" name="teacher" value="<%=c.course_teacher%>">
	<input type="hidden" name="state" value="申请中">
		<div class="x-body layui-anim layui-anim-up">
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="x-red">*</span>课程名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="acname" name="acname" value="<%=c.getcourse_name() %>" required="required"
                   class="layui-input" />
              </div>    
          </div>
          <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>上课时间
              </label>
              <div class="layui-input-inline" >
                   <select id="actime" name="actime" >
                   <option selected="selected"><%=c.getcourse_time() %></option>
             		 <option>周一上午</option>
             		 <option>周一下午</option>
             		 <option>周二上午</option>
             		 <option>周二下午</option>
             		 <option>周三上午</option>
             		 <option>周三下午</option>
             		 <option>周四上午</option>
             		 <option>周四下午</option>
             		 <option>周五上午</option>
             		 <option>周五下午</option>
           		 </select>
              </div>
             
          </div>
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                  <span class="x-red">*</span>上课地点
              </label>
              <div class="layui-input-inline" >
                   <select id="acplace" name="acplace" >
                    <option selected="selected"><%=c.getcourse_place() %></option>
             		 <option>九教101</option>
             		 <option>九教103</option>
             		 <option>九教105</option>
             		 <option>九教107</option>
             		 <option>九教201</option>
             		 <option>九教203</option>
             		 <option>九教205</option>
             		 <option>九教207</option>
           		 </select>
              </div>
            
          </div>
            <input type="hidden" id="teacher" name="teacher" value="<%=ses.getAttribute("User_name") %>" 
                   class="layui-input"/>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>所属学院
              </label>
              <div class="layui-input-inline">
                   <input type="text" id="college" name="college" value="<%=c.getcourse_academy() %>" required="required"
                   class="layui-input"/>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>课程分类
              </label>
              <div class="layui-input-inline">
                   <select name="actype">
                   	<option><%=c.getcourse_type() %></option>
             		 <option>专业课</option>
             		 <option>公选课</option>
           		 </select>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>课程学分
              </label>
              <div class="layui-input-inline">
                  <input type="number" id="credit" name="credit" value="<%=c.getcourse_credit() %>" required="required"
                   class="layui-input"/>
              </div>
          </div>
		
			<div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>课程学期
              </label>
              <div class="layui-input-inline">
                   <select name="cterm">
                  	 <option selected="selected"><%=c.getcourse_term() %></option>
             		 <option>大一上学期</option>
             		 <option>大一下学期</option>
             		 <option>大二上学期</option>
             		 <option>大二下学期</option>
             		 <option>大三上学期</option>
             		 <option>大三下学期</option>
             		 <option>大四上学期</option>
             		 <option>大四下学期</option>
           		 </select>
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>总容量
              </label>
              <div class="layui-input-inline">
                  <input type="number" id="count" name="count" value="<%=c.getcourse_sum() %>" required="required"
                   class="layui-input"/>
              </div>
          </div>
     
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <div>
             	 <input type="submit"  class="layui-btn" value="修改" />
             	  <input type="button"  onclick="window.location.href='acourseList.jsp'" class="layui-btn" value="返回" />
         	</div>
          </div>
           
    </div>
	</form>
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script type="text/javascript" src="../../layui/lay/dest/layui.all.js"></script>
<script> 
	var layer = layui.layer;
  var acadd ='<%=ses.getAttribute("acadd")%>';
  if(acadd=='time'){
	  layer.alert("上课时间冲突！请重新输入")
  }
  if(acadd=='place'){
	  layer.alert("上课地点冲突！请重新输入")
  }
  if(acadd=='else'){
	  layer.alert("上课时间或地点冲突！请重新输入")
  }
</script>
</body>
</html>
