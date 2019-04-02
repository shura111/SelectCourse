<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="cn.ceh.bean.*"%>
<%@page import=" cn.ceh.dao.*" %>
<%@page language="java" import="java.util.* ,java.awt.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
HttpSession ses = request.getSession();
%>
	<title>教师列表</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
	<link rel="stylesheet" href="../../css/news.css" media="all" />
	<link type="text/css" rel="stylesheet" href="../../css/showBo.css" />
	 
	 
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<form class="layui-inline" action="<%=path%>/SelectTeacherServlet" method="post">
		    <div class="layui-input-inline">
		    	<input type="text" id="word" name="word" value="${word }" placeholder="请输入关键字" class="layui-input">
		    </div>
		    <input type="submit" value="查询" class="layui-btn ">
		</form>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal" href="/Webtest/page/teacher/teacherAdd.jsp">添加</a>
		</div>
	</blockquote>

	<div class="layui-form news_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width=12%>
				<col width=12%>
				<col width=12%>
				<col width=12%>
				<col width=12%>
				<col width=12%>
				<col width=12%>
		    </colgroup>
		    <thead>
				<tr>
					<th style="text-align:left;">教师编号</th>
					<th>教师名称</th>
					<th>年龄</th>
					<th>办公室</th>
					<th>所属学院</th>
					<th>性别</th>
					<th>操作</th>
				</tr> 
		    </thead>
		    
			
		    <c:forEach var="o" items="${Teacherlists }">
			<tbody >
				<tr>
						
					<td align="left">${o.getTeacher_id() }</td>
					<td>${o.getTeacher_name() }</td>
					<td>${o.getTeacher_age() }</td>
					<td>${o.getTeacher_office() }</td>
					<td>${o.getTeacher_academy() }</td>
					<td>${o.getTeacher_sex()}</td>
					<td><a class="layui-btn layui-btn-mini " href="/Webtest/page/teacher/teacherUpdate.jsp?tid=${o.getTeacher_id() }" ><i
							class="iconfont icon-edit"></i> 修改</a>
        	 <a class="layui-btn layui-btn-danger layui-btn-mini"   
					href="javascript:var path='<%=path %>';Showbo.Msg.confirm('确定要删除吗？',function(flag){
        	 if(flag=='yes'){
        		 window.location.href=path+'/DelTeacherServlet?id=${o.getTeacher_id()}';
        					 }	 
        	 });" onclick="" ><i class="layui-icon"></i> 删除</a>
					</td>
				</tr>
			</tbody>
			</c:forEach>
		</table>
		<br>
		
		<c:if test="${pageNo>1 }">
			<a href="<%=path %>/SelectTeacherServlet?pageNos=1&word=${word}" > 首页</a>
			<a href="<%=path %>/SelectTeacherServlet?pageNos=${pageNo-1 }&word=${word}" >上一页</a>
		</c:if>
		
		<c:if test="${pageNo<recordCount }">
			<a href="<%=path %>/SelectTeacherServlet?pageNos=${(pageNo+1) }&word=${word}">下一页</a>
			<a href="<%=path %>/SelectTeacherServlet?pageNos=${recordCount}&word=${word}">末页</a>
		
		</c:if>
		<form action="<%=path %>/SelectTeacherServlet">
			<h4 align="center">
			<input type="hidden" name="word" value="${word }">
				共${recordCount}页 <input type="text" value="${pageNo}"
					name="pageNos" size="1">页 <input type="submit" value="到达" >
				
			</h4>
		</form>
		
	</div>
	
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script type="text/javascript" src="../../js/show.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="../../layui/lay/dest/layui.all.js"></script>
	<script> 
	var layer = layui.layer
	 ,form = layui.form();
  var tea ='<%=ses.getAttribute("tea")%>';
  if(tea=='addsuc'){
	  layer.alert("添加成功！")
	  <% ses.removeAttribute("tea");%>
  }
  if(tea=='updatesuc'){
	  layer.alert("修改成功！");
	  <% ses.removeAttribute("tea");%>
  }
  if(tea=='delsuc'){
	  layer.alert("删除成功！");
	  <% ses.removeAttribute("tea");%>
  }
  if(tea=='false'){
	  layer.alert("没有对应记录！");
	  <% ses.removeAttribute("tea");%>
  }
</script>
</body>
</html>
