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
	<link rel="stylesheet" href="../../layui/css/layui.mobile.css" media="all" />
	<link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
	<link rel="stylesheet" href="../../css/news.css" media="all" />
	 <link type="text/css" rel="stylesheet" href="../../css/showBo.css" />
</head>
			<%
					String path=request.getContextPath();
					HttpSession ses = request.getSession();
			%>
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
					<th>总容量</th> 
					<th>操作</th> 
				</tr> 
		    </thead>
				
				<c:forEach var="o" items="${list }">
			<tbody >
				<tr>
					<td align="left" >${o.getcourse_id() }</td>
					<td>${o.getcourse_name() }</td>
					<td>${o.getcourse_time() }</td>
					<td>${o.getcourse_place() }</td>
					<td>${o.getcourse_teacher() }</td>
					<td>${o.getcourse_academy() }</td>
					<td>${o.getcourse_type()}</td>
					<td>${o.getcourse_credit()}</td>
					<td>${o.getcourse_term() }</td>
					<td>${o.getcourse_sum() }</td>
					<c:set var="f" value="0"></c:set>
					<c:if test="${o.getcourse_teacher() eq SesUser.user_name }">
						<td><input type="checkbox" name="switch" value="${o.getcourse_id() }" lay-skin="switch" lay-text="已选|未选" lay-filter="check" checked></td>
					</c:if>
					<c:if test="${empty o.getcourse_teacher()}">
						<td><input type="checkbox" name="switch" value="${o.getcourse_id() }" lay-skin="switch" lay-text="已选|未选" lay-filter="check"></td>
					</c:if>
					<c:if test="${not empty o.getcourse_teacher() && o.getcourse_teacher() ne SesUser.user_name}">
						<td><input type="checkbox" name="switch" value="${o.getcourse_id() }" lay-skin="switch" lay-text="已选|未选" disabled></td>
					</c:if>
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
	;!function(){
		  var layer = layui.layer
		  ,form = layui.form();
		  form.on('switch(check)', function(data){
		  		var id = data.value;
			  var x=data.elem.checked;
			  layer.open({
	                content: '确定要更改吗？'
	                ,btn: ['确定', '取消']
	                ,yes: function(index, layero){
	                    layer.close(index);
	                    $.ajax({
	                		url : "${pageContext.request.contextPath}/TeachooseServlet",
	                		type : "POST",
	                		async : true,
	                		data :	{"state" : x,"id": id},
	                		dataType : "text",
	                		beforeSend:function(){
	                			 index_wx = layer.msg('正在切换中，请稍候',{icon: 16,time:2000,shade:0.8});     
	                			  },
	                			  error: function(data){
	                				  console.log(data);	          
	                		        layer.msg('数据异常，操作失败！8'); 
	                				   },
	                		success : function(flag) {
	                			 if(flag=="1"){ 
	                				 data.elem.checked=x;
	         	                    form.render();
	                				 setTimeout(function(){layer.msg('操作成功！');},2000);  
	                			  }
	                			 else if(flag=='2'){
	                				 data.elem.checked=!x;
	                				 form.render();
	                				 setTimeout(function(){layer.msg('时间冲突！');},2000);  
	                			 }
	                			 else{console.log(flag);layer.msg('数据异常，操作失败！'); }
	                		
	                		},
	                		error : function(xhr) {
	                			alert("错误提示： " + xhr.status + " " + xhr.statusText);
	                	}
	                });
	                    //按钮【按钮一】的回调
	                }
	                ,btn2: function(index, layero){
	                    //按钮【按钮二】的回调
	                    data.elem.checked=!x;
	                    form.render();
	                    layer.close(index);
	                    //return false   开启该代码可禁止点击该按钮关闭
	                }
	                ,cancel: function(){
	                    //右上角关闭回调
	                    data.elem.checked=!x;
	                    form.render();
	                    //return false 开启该代码可禁止点击该按钮关闭
	                }
	            });
	            return false;
		  })
		}();
	        
	        
//取出传回来的参数error并与yes比较
  var cou ='<%=ses.getAttribute("cou")%>';
  if(cou=='zero'){
	  layer.alert("没有选课信息！");
	  <% ses.removeAttribute("cou");%>
  }
</script>
</body>
</html>
