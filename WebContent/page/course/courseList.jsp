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
	<title>课程列表</title>
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
	<blockquote class="layui-elem-quote news_search">
		<form class="layui-inline" action="<%=path%>/SelectAllcourseServlet" method="post" >
		    <div class="layui-input-inline">
		    	<input type="text" id="word" name="word" value="${word }" placeholder="请输入关键字" class="layui-input">
		    </div>
		    <input type="submit" value="查询" class="layui-btn ">
		</form>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal" href="courseAdd.jsp">添加</a>
		</div>
		
		<div class="layui-form layui-inline" style="float:right">
			<label class="layui-form-label">公布选课结果</label>
			<c:if test="${sessionScope.out eq 'yes' }">
      			<input type="checkbox" name="open" value="out" lay-skin="switch" lay-text="ON|OFF" lay-filter="open" checked>
      		</c:if>
      		<c:if test="${empty sessionScope.out}">
      			<input type="checkbox" name="open" value="out" lay-skin="switch" lay-text="ON|OFF" lay-filter="open">
      		</c:if>
    	</div>
    	
		<div class="layui-form layui-inline" style="float:right">
			<label class="layui-form-label">开启教师选课</label>
			<c:if test="${sessionScope.teaopen eq 'yes' }">
      			<input type="checkbox" name="opentea" value="tea" lay-skin="switch" lay-text="ON|OFF" lay-filter="open" checked>
      		</c:if>
      		<c:if test="${empty sessionScope.teaopen}">
      			<input type="checkbox" name="opentea" value="tea" lay-skin="switch" lay-text="ON|OFF" lay-filter="open">
      		</c:if>
    	</div>
    	<div class="layui-form layui-inline" style="float:right">
			<label class="layui-form-label">开启学生选课</label>
			<c:if test="${sessionScope.stuopen eq 'yes' }">
      			<input type="checkbox" name="open" value="stu" lay-skin="switch" lay-text="ON|OFF" lay-filter="open" checked>
      		</c:if>
      		<c:if test="${empty sessionScope.stuopen}">
      			<input type="checkbox" name="open" value="stu" lay-skin="switch" lay-text="ON|OFF" lay-filter="open">
      		</c:if>
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
					<th>课程状态</th>
					<th>课程学期</th>
					<th>总容量</th>
					<th>操作</th>
				</tr> 
		    </thead>
				
				<c:forEach var="o" items="${courselist }">
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
					<c:if test="${o.getcourse_state()=='已审核'}">
						<td><input type="checkbox" name="switch" value="${o.getcourse_id() }" lay-skin="switch" lay-text="开课|申请" lay-filter="check" checked></td>
					</c:if>
					<c:if test="${o.getcourse_state()=='申请中'}">
						<td><input type="checkbox" name="switch" value="${o.getcourse_id() }" lay-skin="switch" lay-text="开课|申请" lay-filter="check"></td>
					</c:if>
					<td>${o.getcourse_term() }</td>
					<td>${o.getcourse_sum() }</td>
					
					<td><a class="layui-btn layui-btn-mini " href="courseUpdate.jsp?aid=${o.getcourse_id()}" ><i
							class="iconfont icon-edit"></i> 修改</a>
					<a class="layui-btn layui-btn-danger layui-btn-mini"   
					href="javascript:var path='<%=path %>';Showbo.Msg.confirm('确定要删除吗？',function(flag){
        	 if(flag=='yes'){
        		 window.location.href=path+'/DelAcourseServlet?id=${o.getcourse_id()}&flag=2';
        					 }	 
        	 });" onclick="" ><i class="layui-icon"></i> 删除</a>
					</td>
				</tr>
			</tbody>
			</c:forEach>
		</table>
		<br>
		
		<c:if test="${pageNo>1 }">
			<a href="<%=path %>/SelectAllcourseServlet?pageNos=1&word=${word}"> 首页</a>
			<a href="<%=path %>/SelectAllcourseServlet?pageNos=${pageNo-1 }&word=${word}">上一页</a>
		</c:if>
		
		<c:if test="${pageNo<recordCount }">
			<a href="<%=path %>/SelectAllcourseServlet?pageNos=${(pageNo+1) }&word=${word}">下一页</a>
			<a href="<%=path %>/SelectAllcourseServlet?pageNos=${recordCount}&word=${word}">末页</a>
		
		</c:if>
		<form action="<%=path %>/SelectAllcourseServlet">
			<h4 align="center">
			<input type="hidden" name="word" value="${word }">
				共${recordCount}页  第<input type="text" value="${pageNo}"
					name="pageNos" size="1">页 <input type="submit"  value="到达">
			</h4>
		</form>
	</div>
	
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script type="text/javascript" src="../../js/show.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="../../layui/lay/dest/layui.all.js"></script>
	<script> 
	;!function(){
		  var layer = layui.layer
		  ,form = layui.form();
		  form.on('switch(open)', function(data){
		  		var id=data.value;
			  var x=data.elem.checked;
			  layer.open({
	                content: '确定要更改吗？'
	                ,btn: ['确定', '取消']
	                ,yes: function(index, layero){
	                    data.elem.checked=x;
	                    form.render();
	                    layer.close(index);
	                    $.ajax({
	                		url : "${pageContext.request.contextPath}/OpenServlet",
	                		type : "POST",
	                		async : true,
	                		data :	{"state" : x,"id": id},
	                		dataType : "text",
	                		beforeSend:function(){
	                			 index_wx = layer.msg('正在切换中，请稍候',{icon: 16,time:1000,shade:0.8});     
	                			  },
	                			  error: function(data){
	                				  console.log(data);	          
	                		        layer.msg('数据异常，操作失败！8'); 
	                				   },
	                		success : function(flag) {
	                			 if(flag=="2"){ 
	                				 setTimeout(function(){layer.msg('发布成功！');},1000);  
	                			  }
	                			 else if(flag=="3"){ 
	                				 setTimeout(function(){layer.msg('关闭成功！');},1000);  
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
		  
		  form.on('switch(check)', function(data){
		  		var id = data.value;
			  var x=data.elem.checked;
			  layer.open({
	                content: '确定要更改吗？'
	                ,btn: ['确定', '取消']
	                ,yes: function(index, layero){
	                    data.elem.checked=x;
	                    form.render();
	                    layer.close(index);
	                    $.ajax({
	                		url : "${pageContext.request.contextPath}/UpdateStateServlet",
	                		type : "POST",
	                		async : true,
	                		data :	{"state" : x,"id": id},
	                		dataType : "text",
	                		beforeSend:function(){
	                			 index_wx = layer.msg('正在切换中，请稍候',{icon: 16,time:1000,shade:0.8});     
	                			  },
	                			  error: function(data){
	                				  console.log(data);	          
	                		        layer.msg('数据异常，操作失败！8'); 
	                				   },
	                		success : function(flag) {
	                			 if(flag=="1"){ 
	                				 setTimeout(function(){layer.msg('操作成功！');},1000);  
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
		  
		  var cou ='<%=ses.getAttribute("cou")%>';
		  if(cou=='addsuc'){
			  layer.alert("添加成功！")
			  <% ses.removeAttribute("cou");%>
		  }
		  if(cou=='updatesuc'){
			  layer.alert("修改成功！");
			  <% ses.removeAttribute("cou");%>
		  }
		  if(cou=='delsuc'){
			  layer.alert("删除成功！");
			  <% ses.removeAttribute("cou");%>
		  }
		  if(cou=='false'){
			  layer.alert("没有对应记录！");
			  <% ses.removeAttribute("cou");%>
		  }
		}();
</script>
</body>
</html>
