<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="cn.ceh.bean.*"%>
<%@page import="cn.ceh.dao.*" %>
<%@page language="java" import="java.util.*,java.awt.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>学生添加--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../../css/font.css">
    <link rel="stylesheet" href="../../css/xadmin.css">
    <link type="text/css" rel="stylesheet" href="../../css/showBo.css" />
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../js/xadmin.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../js/show.js"></script>
    
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <script type="text/javascript" src="../../js/jQuery.js"></script>
</head>
<body class="childrenBody">

			<%
				 String path=request.getContextPath();
				 HttpSession ses = request.getSession();	
				 String id=request.getParameter("tid");
				 Teacherdao dao=new Teacherdao();
				 Teacher t=dao.find(id);
			%>
	<form class="" action="<%=path %>/UpdateTeacherServlet?id=<%=id %>" method="post" onSubmit="return check();">
		<div class="x-body layui-anim layui-anim-up">
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="x-red">*</span>教师姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="tname" name="tname" value="<%=t.teacher_name %>" required="required" maxlength=10 
                  onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'');" class="layui-input" />
              </div>    
          </div>
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="x-red">*</span>年龄
              </label>
              <div class="layui-input-inline">
                  <input type="number" id="tage" name="tage" value="<%=t.teacher_age %>" required="required"
                   class="layui-input" />
              </div>    
          </div>
          <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>性别
              </label>
              <div class="layui-input-inline">
				<select id="tsex" name="tsex">
					<option disabled="disabled"><%=t.teacher_sex %></option>
             		 <option>男</option>
             		 <option>女</option>
           		 </select>
              </div>
          </div>
            
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label" >
                  <span class="x-red">*</span>所属学院
              </label>
              <div class="layui-input-inline">
                  <select id="tacademy" name="tacademy" onchange="getoffice('tacademy')"></select>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>办公室
              </label>
              <div class="layui-input-inline ">
                   <select id="toffice" name="toffice"></select>
              </div>  
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <div>
             	 <input type="submit"  class="layui-btn" value="修改" />
             	  <input type="button"  onclick="window.location.href='teacherList.jsp'" class="layui-btn" value="返回" />
         	</div>
          </div>
           
    </div>
	</form>
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script type="text/javascript">
	$.ajax({
		url : "${pageContext.request.contextPath}/SelectAcademyServlet",
		type : "POST",
		async : true,
		dataType : "json",
		success : function(tacademy) {
			if(tacademy.length>0){
			 	$("#tacademy").empty();
				$("#toffice").empty();
				$("#tacademy").append("<option>"+"<%=t.teacher_academy %>"+"</option>");
				$("#toffice").append("<option>"+"<%=t.teacher_office %>"+"</option>");
				
			}
			for(var i=0;i<tacademy.length;i++){
				$("#tacademy").append("<option>"+tacademy[i]["academy"]+"</option>");
			}
			/* $("#tacademy").trigger("change"); */
		},
		error : function(xhr) {
			alert("错误提示： " + xhr.status + " " + xhr.statusText);
	}
});
	function getoffice(id){
		var selId = document.getElementById(id);       
	       var seleIndex =selId.selectedIndex; 
	       var academy=selId.options[seleIndex].text;
		 $.ajax({
				url : "${pageContext.request.contextPath}/SelectOfficeServlet",
				type : "POST",
				async : true,
				data :"academy="+academy,
				dataType : "json",
				success : function(toffice) {
					if(toffice.length>0){
						 $("#toffice").empty(); 
					}
					for(var i=0;i<toffice.length;i++){
						$("#toffice").append("<option>"+toffice[i]["academy_office"]+"</option>");
					}
				},
				error : function(xhr) {
					alert("错误提示： " + xhr.status + " " + xhr.statusText);
			}
		});
		 
    }
	function check(){ 
		var name=document.getElementById("tname").value;
		if(name.search("!")||name.search("@")||name.search("#")){
			layer.alert('姓名禁止有特殊字符!');
			return false;
			}
		var reg = /[0-9]/;
		if(reg.test(name)){
			layer.alert('姓名禁止有数字!');
			return false;
		}
		
		var age=document.getElementById("tage").value;
		if(age<0||age>200){
			layer.alert('请输入合法年龄!');
			return false;
		}
	} 	

    </script>
</body>
</html>