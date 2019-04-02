<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="cn.ceh.bean.*"%>
<%@page import=" cn.ceh.dao.*" %>
<%@page language="java" import="java.util.* ,java.awt.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>学生课程列表</title>
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
			<%
				String path=request.getContextPath();
				HttpSession ses = request.getSession();
			%>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<form class="layui-inline" action="<%=path%>/SelectStucourseServlet" method="post">
		    <div class="layui-input-inline">
		  		  <input type="text" id="word" name="word" value="${word }" placeholder="请输入关键字" class="layui-input">
		    </div>
		    <input type="submit" value="查询" class="layui-btn ">
		</form>
		
	</blockquote>
	<div class="layui-form news_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="50">
				<col width="50">
				<col width="50">
				<col width="50">
				<col width="50">
				<col width="50">
				<col width="50">
				<col width="50">
				<col width="50">
		    </colgroup>
		    <thead>
				<tr>
					<th style="text-align:left;">课程编号</th>
					<th>课程名称</th>
					<th>上课时间</th>
					<th>上课地点</th>
					<th>授课教师</th>
					<th>所属学院</th>
					<th>课程分类</th>
					<th>课程学分</th>
					<th>课程学期</th>
				</tr> 
		    </thead>
				
			<c:forEach var="o" items="${courselist }" >
			<tbody >
				<tr>
					<td align="left" >${o.getcourse_id() }</td>
					<td>${o.getcourse_name() }</td>
					<td>${o.getcourse_time() }</td>
					<td>${o.getcourse_place() }</td>
					<td>${o.getcourse_teacher() }</td>
					<td>${o.getcourse_academy() }</td>
					<td>${o.getcourse_type() }</td>
					<td>${o.getcourse_credit() }</td>
					<td>${o.getcourse_term()  }</td>
					
				</tr>
			</tbody>
			</c:forEach>
		</table>
		<br>
		<c:if test="${pageNo>1 }">
			<a href="<%=path %>/SelectStucourseServlet?pageNos=1&word=${word}"> 首页</a>
			<a href="<%=path %>/SelectStucourseServlet?pageNos=${pageNo-1 }&word=${word}">上一页</a>
		</c:if>
		
		<c:if test="${pageNo<recordCount }">
			<a href="<%=path %>/SelectStucourseServlet?pageNos=${(pageNo+1) }&word=${word}">下一页</a>
			<a href="<%=path %>/SelectStucourseServlet?pageNos=${recordCount}&word=${word}">末页</a>
		
		</c:if>
		<form action="<%=path %>/SelectStucourseServlet">
			<h4 align="center">
				<input type="hidden" name="word" value="${word }">
				共${recordCount}页 <input type="text" value="${pageNo}"
					name="pageNos" size="1">页 <input type="submit" value="到达">
			</h4>
		</form>
	</div>
	
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script type="text/javascript" src="../../js/show.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="../../layui/lay/dest/layui.all.js"></script>
	<script type="text/javascript">
	var layer = layui.layer
	 ,form = layui.form();
	var cou='<%=ses.getAttribute("cou") %>'
	if(cou=='false'){
		layer.alert("没有查到对应记录!");
		  <% ses.removeAttribute("stu");%>
	  }
	</script>
</body>
</html>
