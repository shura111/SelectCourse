<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>个人资料--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../../css/user.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form">
		<div class="user_left">
			<c:if test="${SesUser.getUser_type()=='学生' }">
				<div class="layui-form-item">
			    <label class="layui-form-label">学生id</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.student_id }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">学生姓名</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.student_name }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">性别</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.student_sex }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">所属学院</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.student_academy }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">所属专业</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.student_professional }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">所属班级</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.student_class }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			</c:if>
			
			<c:if test="${SesUser.getUser_type()=='教师' }">
				<div class="layui-form-item">
			    <label class="layui-form-label">教师id</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.teacher_id }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">教师姓名</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.teacher_name }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">年龄</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.teacher_age }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">性别</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.teacher_sex }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">所属学院</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.teacher_academy }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">办公室</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${Info.teacher_office }" disabled class="layui-input layui-disabled">
			    </div>
			</div>
			</c:if>
		</div>
	</form>
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script type="text/javascript" src="address.js"></script>
	<script type="text/javascript" src="user.js"></script>
</body>
</html>