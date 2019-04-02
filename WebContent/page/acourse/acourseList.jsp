<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="cn.ceh.bean.Course"%>
<%@page import="cn.ceh.dao.Coursedao" %>
<%@page language="java" import="java.util.*,java.awt.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>申请课程列表</title>
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
						ses.setAttribute("aid", null);
			%>
<body class="childrenBody">
			
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-inline">
		    <div class="layui-input-inline">
		    	<input type="text" value="" placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		    <a class="layui-btn search_btn">查询</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal" href="acourseAdd.jsp">添加</a>
		</div>
		
		
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
				<col width="50">
				<col width="50">
				<col width="50">
				<col width="50">
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox"></th>
					<th style="text-align:left;">课程编号</th>
					<th>课程名称</th>
					<th>上课时间</th>
					<th>上课地点</th>
					<th>授课教师</th>
					<th>所属学院</th>
					<th>课程分类</th>
					<th>课程学分</th>
					<th>申请状态</th>
					<th>课程学期</th>
					<th>总容量</th>
					<th>操作</th>
				</tr> 
		    </thead>
				
				<c:forEach var="o" items="${acourselist }">
			<tbody >
				<tr>
					<td><input type="checkbox" name="checked" 
						>
					<div class="layui-unselect layui-form-checkbox" >
							<i class="layui-icon"></i>
						</div></td>
					<td align="left" >${o.getcourse_id() }</td>
					<td>${o.getcourse_name() }</td>
					<td>${o.getcourse_time() }</td>
					<td>${o.getcourse_place() }</td>
					<td>${o.getcourse_teacher() }</td>
					<td>${o.getcourse_academy() }</td>
					<td>${o.getcourse_type()}</td>
					<td>${o.getcourse_credit()}</td>
					<td>${o.getcourse_state() }</td>
					<td>${o.getcourse_term() }</td>
					<td>${o.getcourse_sum() }</td>
					
					<td><a class="layui-btn layui-btn-mini " href="acourseUpdate.jsp?aid=${o.getcourse_id() }" ><i
							class="iconfont icon-edit"></i> 修改</a>
					<a class="layui-btn layui-btn-danger layui-btn-mini"   
					href="javascript:var path='<%=path %>';Showbo.Msg.confirm('确定要删除吗？',function(flag){
        	 if(flag=='yes'){
        		 window.location.href=path+'/DelAcourseServlet?id=${o.getcourse_id() }&flag=1';
        					 }	 
        	 });" onclick="" ><i class="layui-icon "></i> 删除</a>
					</td>
				</tr>
			</tbody>
			</c:forEach>
		</table>
	</div>
	
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script type="text/javascript" src="../../js/show.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="../../layui/lay/dest/layui.all.js"></script>
	<script> 
	var layer = layui.layer
	 ,form = layui.form();
  var acadd ='<%=ses.getAttribute("acadd")%>';
  if(acadd=='addsuc'){
	  layer.alert("添加成功！")
	  <% ses.removeAttribute("acadd");%>
  }
  if(acadd=='updatesuc'){
	  layer.alert("修改成功！");
	  <% ses.removeAttribute("acadd");%>
  }
  if(acadd=='delsuc'){
	  layer.alert("删除成功！");
	  <% ses.removeAttribute("acadd");%>
  }
</script>
</body>
</html>
