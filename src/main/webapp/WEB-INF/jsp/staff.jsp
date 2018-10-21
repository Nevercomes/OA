<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/home.css">
	<script src="<%=path%>/plugins/jquery/jquery-3.2.1.min.js"></script>
	<script src="<%=path%>/plugins/json/jquery.json.min.js"></script>
	<script src="<%=path%>/js/staff/assessStaff.js"></script>
	<script src="<%=path%>/js/utils/enum.js"></script>
	<script src="<%=path%>/js/utils/timeUtil.js"></script>
	<script src="<%=path%>/js/utils/session.js"></script>
	<script src="<%=path%>/js/account/login.js"></script>
</head>
<body ondragstart="return false" onload="f()">
	<div class="top">
		<img src="<%=path%>/image/logo_w.png" alt="logo" id="logo">
		<img src="<%=path%>/image/hua.png" alt="hua" class="hua">
		<div class="date">
			<span class="date_font" id="date_year"></span>
			<img src="<%=path%>/image/gang.png" alt="gang">
			<span class="date_font" id="date_month"></span>
		</div>
	</div>
	<div class="left">
		<div style="float: right;height: 100%;width: 9%;margin-right: 3%;display: flex;align-items: center;">
			<img src="<%=path%>/image/left.png" alt="left" id="left_ico" class="pointer">
			<img src="<%=path%>/image/right.png" alt="right" id="right_ico" class="pointer" style="display: none;">
		</div>
		<div class="contest">
			<div class="top_contest">
				<span>信息技术中心</span>
				<span id="g_department">${sessionScope.department}</span>
				<span id="g_name">${sessionScope.name}</span>
				<span id="g_id">${sessionScope.userId}</span>
			</div>
			<div class="buttom_contest">
				<span id="toFill">填写考核表</span>
				<span id="toSee">查看考核结果</span>
				<span id="toRe">修改密码</span>
			</div>
		</div>
	</div>
	<div class="main">
		<div class="board">
			<form id="fill" style="">
				<div class="to_fill">
					<label for="assessWorkRegular">常规工作</label>
					<textarea name="assessWorkRegular" id="assessWorkRegular" cols="30" rows="1"></textarea>
				</div>
				<div class="to_fill">
					<label for="assessWorkOutPlan">计划外工作</label>
					<textarea name="assessWorkOutPlan" id="assessWorkOutPlan" cols="30" rows="1"></textarea>
				</div>
				<div class="to_fill">
					<label for="assessExpanse">其他开支</label>
					<textarea name="assessExpanse" id="assessExpanse" cols="30" rows="1"></textarea>
				</div>
				<div class="to_fill">
					<label for="assessOther">其他加班</label>
					<textarea name="assessOther" id="assessOther" cols="30" rows="1"></textarea>
				</div>
				<div class="to_fill">
					<label for="assessPlanSimple">工作计划</label>
					<textarea name="assessPlanSimple" id="assessPlanSimple" cols="30" rows="1"></textarea>
				</div>
				<div class="fill_button">
					<div class="fill_b">
						<span style="position: relative;left: 2px;" id="filename">文件上传</span>
						<input type="file" class="real_file">
					</div>
					<input type="button" class="fill_b" onclick="submitAssessment()" value="提交">
				</div>
			</form>
			<form id="see" style="display: none;">
				<div class="head">
					<div class="dep">
						<span>部门：</span>
						<select name="department" id="">
							<option value="" selected="selected">请选择</option>
							<option value="yanfa">研发部</option>
							<option value="wangluo">网络部</option>
							<option value="meigong">美工部</option>
						</select>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script src="<%=path%>/js/home.js"></script>
</html>