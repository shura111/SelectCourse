<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="cn.ceh.bean.*"%>
<%@page import=" cn.ceh.dao.*" %>
<%@page language="java" import="java.util.* ,java.awt.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>学生列表</title>
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
	 
	  <link rel="stylesheet" href="${basepath}layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
	<link rel="stylesheet" href="${basepath}css/news.css" media="all" />
	 <link type="text/css" rel="stylesheet" href="${basepath}css/showBo.css" />

</head>
<body class="childrenBody">
	<div class="layui-form news_list">
	  	<table class="layui-table">
	  	
		    <colgroup>
				<col width="50">
				<col width="50">
				<col width="50">
				<col width="50">
				<col width="50">
				<col width="50">
		    </colgroup>
		    <thead>
				<tr>
					
					<th style="text-align:left;">学生编号</th>
					<th>学生名称</th>
					<th>班级</th>
					<th>性别</th>
					<th>所属学院</th>
					<th>所学专业</th>
					
				</tr> 
		    </thead>
		    
			
		    <c:forEach var="o" items="${Studentlists }">
			<tbody >
				<tr>
					<td align="left">${o.getStudent_id() }</td>
					<td>${o.getStudent_name() }</td>
					<td>${o.getStudent_class() }</td>
					<td>${o.getStudent_sex() }</td>
					<td>${o.getStudent_academy() }</td>
					<td>${o.getStudent_professional()}</td>
					
				</tr>
			</tbody>
			</c:forEach>
		</table>
		<br>
		
		<c:if test="${pageNo>1 }">
			<a href="StudentServlet?pageNos=1&id=${id }"> 首页</a>
			<a href="StudentServlet?pageNos=${pageNo-1 }&id=${id }">上一页</a>
		</c:if>
		
		
		<c:if test="${pageNo<recordCount }">
			<a href="StudentServlet?pageNos=${(pageNo+1) }&id=${id }">下一页</a>
			<a href="StudentServlet?pageNos=${recordCount}&id=${id }">末页</a>
		
		</c:if>
		<form action="<%=path %>/StudentServlet">
			<h4 align="center">
				
				共${recordCount}页 <input type="text" value="${pageNo}"
					name="pageNos" size="1">页 <input type="hidden" name="id" value=""><input type="submit" value="到达">
				
			</h4>
		</form>
		<a href="page/sc/courseList.jsp">返回</a>
	</div>
	
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<!--  <script type="text/javascript" src="acourseList.js"></script>-->
	<script type="text/javascript" src="../../js/show.js"></script>
	
</body>
</html>