<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>课程添加--layui后台管理模板</title>
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
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="../../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../js/xadmin.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../js/show.js"></script>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />

</head>
<body class="childrenBody">
			<%
				String path=request.getContextPath();
			    HttpSession ses = request.getSession();	
			%>
	<form class="layui-form" action="<%=path %>/AcaddServlet?flag=2" id="myForm" method="post" >
		<div class="x-body layui-anim layui-anim-up">
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="x-red">*</span>课程名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="cname" name="cname" value="" required="required" maxlength=15 onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'');"
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
        	<input type="hidden" name="teacher" value="" />
        	<input type="hidden" name="state" value="已审核" />
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="x-red">*</span>所属学院
              </label>
              <div class="layui-input-inline">
                   <select id="college" name="college"></select>
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
                  <input type="text" id="credit" name="credit" value="" placeholder="请保留一位小数" maxlength=3 onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'');"
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
                  <input type="number" id="count" name="count" value="" required="required"  onkeyup="this.value=this.value.replace(/(^\s+)|(\s+$)/g,'');"
                   class="layui-input"/>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <div>
             	 <input type="button" id="btn"  class="layui-btn" value="添加" />
             	  <input type="button"  onclick="window.location.href='courseList.jsp'" class="layui-btn layui-btn-normal" value="返回" />
         	</div>
          </div>
           
    </div>
	</form>
	<script type="text/javascript" src="../../layui/layui.js"></script>
	<script> 
	$.ajax({
		url : "${pageContext.request.contextPath}/SelectAcademyServlet",
		type : "POST",
		async : true,
		dataType : "json",
		success : function(college) {
			if(college.length>0){
			 	$("#college").empty();
				$("#college").append("<option>"+"--请选择学院--"+"</option>");
 		}
			for(var i=0;i<college.length;i++){
				$("#college").append("<option>"+college[i]["academy"]+"</option>");
			}
		
		},
		error : function(xhr) {
			alert("错误提示： " + xhr.status + " " + xhr.statusText);
	}
});
	layui.use('form',function(){
		var form = layui.form;
        //刷新界面 所有元素
		/* form.render('select'); */
});
	$(document).on('click','#btn',function(){
			var options1=$("#ctime option:selected");
			var options2=$("#cplace option:selected");
			var options3=$("#term option:selected");
			var ctime=options1.text();
			var cplace=options2.text();
			var term=options3.text();
			var teacher=null;
			var options=$("#college option:selected");
			var cname=document.getElementById("cname").value; 
			if(cname == '' || cname == undefined || cname == null){
				layer.alert('请填写课程名称!');
				return false;
			}
			var credit=document.getElementById("credit").value; 
			if(credit == '' || credit == undefined || credit == null){
				layer.alert('请填写课程学分!');
				return false;
			}
			if('' != credit.replace(/\d{1,}\.{0,1}\d{0,}/,'')){
				layer.alert('学分格式不正确!');
				return false;
			}
			var count=document.getElementById("count").value; 
			if(count == '' || count == undefined || count == null){
				layer.alert('请填写课程容量!');
				return false;
			}
			if(count<0||count>300){
				layer.alert('课程容量不合法!');
				return false;
			}
			var s=options.text();
			if(s=="--请选择学院--"){ 
				layer.alert('请选择学院!');
				return false;
				} 
			else{
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
			}
	});
	/* function check(){ 
		var options=$("#college option:selected");
		var s=options.text();
		if(s=="--请选择学院--"){ 
			Showbo.Msg.alert('请选择学院!');
			return false;
			} 
		}  */
</script>
</body>
</html>
