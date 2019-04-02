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
	<title>学生列表</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
	  <link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../../css/news.css" media="all" />
	 <link type="text/css" rel="stylesheet" href="../../css/showBo.css" />
	 
	 
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<form class="layui-inline" action="<%=path%>/SelectStudentServlet" method="post">
		    <div class="layui-input-inline">
		  		  <input type="text" id="word" name="word" value="${word }" placeholder="请输入关键字" class="layui-input">
		    </div>
		    <input type="submit" value="查询" class="layui-btn ">
		</form>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal" href="/Webtest/page/student/studentAdd.jsp">添加</a>
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
					<th style="text-align:left;">学生编号</th>
					<th>学生名称</th>
					<th>班级</th>
					<th>性别</th>
					<th>所属学院</th>
					<th>所学专业</th>
					<th>操作</th>
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
					<td><a class="layui-btn layui-btn-mini " href="/Webtest/page/student/studentUpdate.jsp?sid=${o.getStudent_id() }" ><i
							class="iconfont icon-edit"></i> 修改</a>
					<a class="layui-btn layui-btn-danger layui-btn-mini"   
					href="javascript:var path='<%=path %>';Showbo.Msg.confirm('确定要删除吗？',function(flag){
        	 if(flag=='yes'){
        		 window.location.href=path+'/DelStudentServlet?id=${o.getStudent_id() }';
        					 }	 
        	 });" onclick="" ><i class="layui-icon "></i> 删除</a>
					</td>
				</tr>
			</tbody>
			</c:forEach>
		</table>
		<br>
		
		<c:if test="${pageNo>1 }">
			<a href="<%=path %>/SelectStudentServlet?pageNos=1&word=${word}"> 首页</a>
			<a href="<%=path %>/SelectStudentServlet?pageNos=${pageNo-1 }&word=${word}">上一页</a>
		</c:if>
		
		
		<c:if test="${pageNo<recordCount }">
			<a href="<%=path %>/SelectStudentServlet?pageNos=${(pageNo+1) }&word=${word}">下一页</a>
			<a href="<%=path %>/SelectStudentServlet?pageNos=${recordCount}&word=${word}">末页</a>
		
		</c:if>
		<form action="<%=path %>/SelectStudentServlet">
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
	<script> 
	var layer = layui.layer
	 ,form = layui.form();
  var stu ='<%=ses.getAttribute("stu")%>';
  if(stu=='addsuc'){
	  layer.alert("添加成功！")
	  <% ses.removeAttribute("stu");%>
  }
  if(stu=='updatesuc'){
	  layer.alert("修改成功！");
	  <% ses.removeAttribute("stu");%>
  }
  if(stu=='delsuc'){
	  layer.alert("删除成功！");
	  <% ses.removeAttribute("stu");%>
  }
  if(stu=='false'){
	  layer.alert("没有对应记录!");
	  <% ses.removeAttribute("stu");%>
  }
</script>
</body>
</html>
