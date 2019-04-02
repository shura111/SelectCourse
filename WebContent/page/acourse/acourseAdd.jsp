<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>课程申请--layui后台管理模板</title>
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
    <script type="text/javascript" src="../../lib/layui/layui.js" charset="utf-8"></script>
   <!--  <script type="text/javascript" src="../../js/xadmin.js" charset="utf-8"></script> -->
    <script type="text/javascript" src="../../js/show.js"></script>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
</head>
<body class="childrenBody">
			<%
				String path=request.getContextPath();
				HttpSession ses = request.getSession();
			%>
	<form class="layui-form" action="<%=path %>/AcaddServlet?flag=1" id="myForm" method="post">
		<input type="hidden" id="teacher" name="teacher" value="${sessionScope.SesUser.user_name }" />
		<input type="hidden" id="state" name="state" value="申请中" />
		<div class="x-body layui-anim layui-anim-up">
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="x-red">*</span>课程名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="cname" name="cname" value="" required="required"
                   class="layui-input" />
              </div>    
          </div>
          <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>上课时间
              </label>
              <div class="layui-input-inline" >
                   <select id="ctime" name="ctime" >
             		 <option>周一上午</option>
             		 <option>周一下午</option>
             		 <option>周二上午</option>
             		 <option>周二下午</option>
             		 <option>周三上午</option>
             		 <option>周三下午</option>
             		 <option>周四上午</option>
             		 <option>周四下午</option>
             		 <option>周五上午</option>
             		 <option>周五下午</option>
           		 </select>
              </div>
             
          </div>
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                  <span class="x-red">*</span>上课地点
              </label>
              <div class="layui-input-inline" >
                   <select id="cplace" name="cplace" >
             		 <option>九教101</option>
             		 <option>九教103</option>
             		 <option>九教105</option>
             		 <option>九教107</option>
             		 <option>九教201</option>
             		 <option>九教203</option>
             		 <option>九教205</option>
             		 <option>九教207</option>
             		 <option>五教101</option>
             		 <option>五教103</option>
             		 <option>五教201</option>
             		 <option>五教203</option>
             		 <option>体育场</option>
           		 </select>
              </div>
            
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>所属学院
              </label>
              <div class="layui-input-inline">
             	  <select name="college" id="college">
             		 	<option>计算机科学与技术学院</option>
             			 <option>物理与光电学院</option>
             			 <option>外国语学院</option>
           		 	</select>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>课程分类
              </label>
              <div class="layui-input-inline">
                   <select name="ctype">
             		 <option>专业课</option>
             		 <option>公选课</option>
           		 </select>
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>课程学分
              </label>
              <div class="layui-input-inline">
                  <input type="number" id="credit" name="credit" value="" required="required"
                   class="layui-input"/>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>课程学期
              </label>
              <div class="layui-input-inline">
                   <select id="term" name="term">
             		 <option>大一上学期</option>
             		 <option>大一下学期</option>
             		 <option>大二上学期</option>
             		 <option>大二下学期</option>
             		 <option>大三上学期</option>
             		 <option>大三下学期</option>
             		 <option>大四上学期</option>
             		 <option>大四下学期</option>
           		 </select>
              </div>
          </div>
		
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>总容量
              </label>
              <div class="layui-input-inline">
                  <input type="number" id="count" name="count" value="" required="required"
                   class="layui-input"/>
              </div>
          </div>
     
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <div>
             	 <input type="button" id="btn" class="layui-btn" value="添加" />
             	  <input type="button"  onclick="window.location.href='acourseList.jsp'" class="layui-btn" value="返回" />
         	</div>
          </div>
    </div>
	</form>
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="../../layui/lay/dest/layui.all.js"></script>
	<script> 
		var layer = layui.layer
	 	 ,form = layui.form();
		$(document).on('click','#btn',function(){
			var options1=$("#ctime option:selected");
			var options2=$("#cplace option:selected");
			var options3=$("#term option:selected");
			var ctime=options1.text();
			var cplace=options2.text();
			var term=options3.text();
			var l="";
			var teacher=document.getElementById("teacher").value;
			$.ajax({
        		url : "${pageContext.request.contextPath}/CheckAddcouServlet",
        		type : "POST",
        		async : true,
        		data :	{"ctime" : ctime,"cplace": cplace, "term": term, "teacher": teacher},
        		dataType : "text",
        		success : function(flag) {
        			if(flag=="suc"){
        				document.getElementById("myForm").submit();
        			}
        			else if(flag=="time"){ 
        				 setTimeout(function(){layer.alert('时间冲突！');},1000);
        			  }
        			 else if(flag=="place"){
        				 setTimeout(function(){layer.alert('地点冲突！');},1000);  
        			  }
        			 else{console.log(flag);layer.msg('数据异常，操作失败！'); }
        		},
        		error : function(xhr) {
        			alert("错误提示： " + xhr.status + " " + xhr.statusText);
        	}
        });
	});
</script>
</body>
</html>
