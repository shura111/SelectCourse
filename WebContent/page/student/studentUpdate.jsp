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
				 String id=request.getParameter("sid");
				 Studentdao dao=new Studentdao();
				 Student s=dao.find(id);
			%>
	<form class="" action="<%=path %>/UpdateStudentServlet?id=<%=id %>" method="post" onSubmit="return check();">
		<div class="x-body layui-anim layui-anim-up">
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="x-red">*</span>学生姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="sname" name="sname" value="<%=s.student_name %>" required="required" maxlength=10 onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'');"
                   class="layui-input" />
              </div>    
          </div>
          <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>性别
              </label>
              <div class="layui-input-inline">
				<select id="ssex" name="ssex" onchange="">
				<% if(s.student_sex.equals("男")) {%>
             		 <option selected="selected">男</option>
             		 <option>女</option>
             		 <%}else{ %>
             		  <option >男</option>
             		 <option selected="selected">女</option>
             		 <%} %>
           		 </select>
              </div>
          </div>
            
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>所属学院
              </label>
              <div class="layui-input-inline select">
                  <select id="sacademy" name="sacademy" onchange="getpro('sacademy')">
           		 </select>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>所学专业
              </label>
              <div class="layui-input-inline select">
                  <select id="sprofessional" name="sprofessional" onchange="getcla('sprofessional')">
           		 </select>
              </div>  
          </div>
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                  <span class="x-red">*</span>专业班级
              </label>
              <div class="layui-input-inline select">
                  <select id="sclass" name="sclass" >
           		 </select>
              </div>  
            
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <div>
             	 <input type="submit"  class="layui-btn" value="修改" />
             	  <input type="button"  onclick="window.location.href='studentList.jsp'" class="layui-btn" value="返回" />
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
		success : function(sacademy) {
			if(sacademy.length>0){
			 	$("#sacademy").empty();
				$("#sprofessional").empty();
				$("#sacademy").append("<option>"+"<%=s.student_academy %>"+"</option>");
				$("#sprofessional").append("<option>"+"<%=s.student_professional %>"+"</option>");
				$("#sclass").append("<option>"+"<%=s.student_class %>"+"</option>"); 
			}
			for(var i=0;i<sacademy.length;i++){
				$("#sacademy").append("<option>"+sacademy[i]["academy"]+"</option>");
			}
			/*  $("#sacademy").trigger("change");  */
		},
		error : function(xhr) {
			alert("错误提示： " + xhr.status + " " + xhr.statusText);
	}
});
	function getpro(id){
			var selId = document.getElementById(id);       
	       var seleIndex =selId.selectedIndex; 
	       var academy=selId.options[seleIndex].text;
		 $.ajax({
				url : "${pageContext.request.contextPath}/SelectProServlet",
				type : "POST",
				async : true,
				data :"academy="+academy,
				dataType : "json",
				success : function(sprofessional) {
					if(sprofessional.length>0){
						$("#sprofessional").empty();
						$("#sclass").empty();
						
					}
					for(var i=0;i<sprofessional.length;i++){
						$("#sprofessional").append("<option>"+sprofessional[i]["professional"]+"</option>");
					}
					$("#sprofessional").trigger("change");
				},
				error : function(xhr) {
					alert("错误提示： " + xhr.status + " " + xhr.statusText);
			}
		});
		 
    }
	function getcla(id){
			var selId = document.getElementById(id);       
	       var seleIndex =selId.selectedIndex; 
	       var professional=selId.options[seleIndex].text;
		 $.ajax({
				url : "${pageContext.request.contextPath}/SelectClassServlet",
				type : "POST",
				async : true,
				data :"professional="+professional,
				dataType : "json",
				success : function(sclass) {
					if(sclass.length>0){
						$("#sclass").empty();
						
					}
					
					for(var i=0;i<sclass.length;i++){
						$("#sclass").append("<option>"+sclass[i]["cla"]+"</option>");
					}
				},
				error : function(xhr) {
					alert("错误提示： " + xhr.status + " " + xhr.statusText);
			}
		});
    }
	function check(){ 
		var name=document.getElementById("sname").value;
		if(name.search("!")||name.search("@")||name.search("#")){
			layer.alert('姓名禁止有特殊字符!');
			return false;
			}
		var reg = /[0-9]/;
		if(reg.test(name)){
			layer.alert('姓名禁止有数字!');
			return false;
		}
		
	} 	
    </script>
</body>
</html>