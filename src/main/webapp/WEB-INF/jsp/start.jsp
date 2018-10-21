<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>start</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<%=path%>/bootstrap4/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=path%>/bootstrap4/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="<%=path%>/css/customize.css">

    <script src="<%=path%>/plugins/jquery/jquery-3.2.1.min.js"></script>
    <script src="<%=path%>/bootstrap4/js/bootstrap.min.js"></script>
    <script src="<%=path%>/plugins/json/jquery.json.min.js"></script>
    <script src="<%=path%>/js/home.js"></script>
    <script src="<%=path%>/js/account/login.js"></script>
    <script src="<%=path%>/js/staff/assess.js"></script>
    <script src="<%=path%>/js/staff/result.js"></script>
    <script src="<%=path%>/js/staff/plan.js"></script>
    <script src="<%=path%>/js/utils/enum.js"></script>
    <script src="<%=path%>/js/utils/timeUtil.js"></script>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img id="test" src="<%=path%>/image/logo.png" width="150" height="40">
    </a>
    <h4>信息技术中心</h4>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
        </ul>
        <nav class="navbar navbar-light bg-light">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">欢迎 <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <span id="span-bar-dep" class="nav-link" href="#">${sessionScope.department}</span>
                </li>
                <li class="nav-item">
                    <span id="span-bar-position" class="nav-link" href="#">${sessionScope.position}</span>
                </li>
                <li class="nav-item dropdown">
                    <a id="a-bar-name" class="nav-link dropdown-toggle " href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${sessionScope.name}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">修改密码</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">登出</a>
                    </div>
                </li>
                <li class="nav-item">
                    <span id="span-bar-date" class="nav-link" href="#">2018/10</span>
                </li>
            </ul>
        </nav>
    </div>
</nav>

<div class="row">
    <div class="col-lg-2 col-md-3 col-ms-5 col-4 ml-2">
        <ul class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <li class="nav-item mt-2">
                <a class="nav-link disabled" href="#">For Staff</a>
            </li>
            <li class="divider"></li>
            <li class="nav-item mb-3">
                <a class="nav-link active" id="v-pills-fill-tab" data-toggle="pill" href="#v-pills-assessment"
                   role="tab"
                   aria-controls="v-pills-assessment" aria-selected="true" onclick="fillAssessment()">绩效考核</a>
            </li>
            <li class="nav-item mb-3">
                <a class="nav-link" id="v-pills-plan-tab" data-toggle="pill" href="#v-pills-plan" role="tab"
                   aria-controls="v-pills-plan" aria-selected="true" onclick="viewPlan()">工作计划</a>
            </li>
            <li class="nav-item mb-3">
                <a class="nav-link" id="v-pills-view-tab" data-toggle="pill" href="#v-pills-result" role="tab"
                   aria-controls="v-pills-result" aria-selected="false" onclick="viewResult()">查看结果</a>
            </li>
        </ul>
    </div>
    <div class="col-lg-9 col-md-8 col-ms-6 col-7 ">
        <div class="row">
            <ul class="nav nav-pills mb-2 mt-2" id="pills-dep-tab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="pills-center-tab" data-toggle="pill" href="#pills-home" role="tab"
                       aria-controls="pills-home" aria-selected="true">中心</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-rd-tab" data-toggle="pill" href="#pills-profile" role="tab"
                       aria-controls="pills-profile" aria-selected="false">研发</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-networks-tab" data-toggle="pill" href="#pills-contact" role="tab"
                       aria-controls="pills-contact" aria-selected="false">网络</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-art-tab" data-toggle="pill" href="#pills-contact" role="tab"
                       aria-controls="pills-contact" aria-selected="false">美工</a>
                </li>
            </ul>
        </div>
        <div class="row">
            <ul class="nav nav-pills mb-3" id="pills-month-tab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="pills-jan-tab" data-toggle="pill" href="#pills-home" role="tab"
                       aria-controls="pills-home" aria-selected="true">Jan</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-feb-tab" data-toggle="pill" href="#pills-profile" role="tab"
                       aria-controls="pills-profile" aria-selected="false">Feb</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-mar-tab" data-toggle="pill" href="#pills-contact" role="tab"
                       aria-controls="pills-contact" aria-selected="false">Mar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-apr-tab" data-toggle="pill" href="#pills-contact" role="tab"
                       aria-controls="pills-contact" aria-selected="false">Apr</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-may-tab" data-toggle="pill" href="#pills-home" role="tab"
                       aria-controls="pills-home" aria-selected="true">May</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-june-tab" data-toggle="pill" href="#pills-profile" role="tab"
                       aria-controls="pills-profile" aria-selected="false">June</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-july-tab" data-toggle="pill" href="#pills-contact" role="tab"
                       aria-controls="pills-contact" aria-selected="false">July</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-aug-tab" data-toggle="pill" href="#pills-contact" role="tab"
                       aria-controls="pills-contact" aria-selected="false">Aug</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-sept-tab" data-toggle="pill" href="#pills-home" role="tab"
                       aria-controls="pills-home" aria-selected="true">Sept</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-oct-tab" data-toggle="pill" href="#pills-profile" role="tab"
                       aria-controls="pills-profile" aria-selected="false">Oct</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-nov-tab" data-toggle="pill" href="#pills-contact" role="tab"
                       aria-controls="pills-contact" aria-selected="false">Nov</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-dec-tab" data-toggle="pill" href="#pills-contact" role="tab"
                       aria-controls="pills-contact" aria-selected="false">Dec</a>
                </li>
            </ul>
        </div>
        <div class="row">
            <div class="offset-2">
                <div class="tab-content mt-4" id="v-pills-tabContent">
                    <div class="tab-pane fade show active" id="v-pills-assessment" role="tabpanel"
                         aria-labelledby="v-pills-assessment-tab">
                        <div class="row">
                            <div class="col-lg-9">
                                <table class="table" border="1">
                                    <thead>
                                    <tr>
                                        <th scope="col" colspan="6">云麓谷信息技术中心<span id="span-assess-month">九</span>月员工个人绩效考核表
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th scope="col">姓名</th>
                                        <td>
                                            <span id="span-assess-name">白云舒</span>
                                        </td>
                                        <th scope="col">部门</th>
                                        <td>
                                            <span id="span-assess-dep">研发部</span>
                                        </td>
                                        <th scope="col">日期</th>
                                        <td>
                                            <span id="span-assess-date">2018-10-18</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">常规</br>
                                            工作
                                        </th>
                                        <td colspan="5">
                                            <textarea id="ta-assess-regular" rows="4"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">计划外</br>
                                            工作
                                        </th>
                                        <td colspan="5">
                                            <textarea id="ta-assess-out" rows="4"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">其它</br>
                                            加班
                                        </th>
                                        <td colspan="5">
                                            <textarea id="ta-assess-other" rows="4"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">其它</br>
                                            开支
                                        </th>
                                        <td colspan="5">
                                            <input id="text-assess-expanse" type="text" name="">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">工作</br>
                                            计划(简)
                                        </th>
                                        <td colspan="5">
                                            <textarea id="ta-assess-plan" rows="4"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" rowspan="2">部长</br>
                                            评价
                                        </th>
                                        <td colspan="4" rowspan="2">
                                            <textarea id="ta-assess-head-eva" rows="4"></textarea>
                                        </td>
                                        <td>
                                            评分
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input id="text-assess-head-score" type="text" name="">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" rowspan="2">总监</br>
                                            评价
                                        </th>
                                        <td colspan="4" rowspan="2">
                                            <textarea id="ta-assess-dir-eva" rows="4"></textarea>
                                        </td>
                                        <td>
                                            评分
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input id="text-assess-dir-score" type="text" name="">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">备注</th>
                                        <td colspan="5">
                                            <textarea id="ta-assess-remark" rows="4"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row" colspan="6">中南大学网络思想政治工作中心</th>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-lg-2" id="div-fill-submit">
                                <input id="btn-fill-submit" type="button" name="" value="提交" onclick="submitAssessment()">
                                <input id="btn-file-mission" type="button" name="" value="上传任务文件">
                                <input id="btn-file-other" type="button" name="" value="上传其它文件">
                                <p>
                                    <span id="span-hint-month">十</span>月考核表</br>
                                    上交时间为</br>
                                    <span id="span-hint-date">11-1-11-7</span>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="v-pills-result" role="tabpanel" aria-labelledby="v-pills-result-tab">
                        <div class="row">
                            <div class="col-lg-9">
                                <table class="table" border="1">
                                    <thead>
                                    <tr>
                                        <th scope="col" colspan="5">云麓谷信息技术中心
                                            <span id="span-result-dep">研发部</span>
                                            <span id="span-result-month">九</span>
                                            月考核结果公示
                                        </th>
                                    </tr>
                                    <tr>
                                        <th scope="col">
                                            <span>部门</span>
                                        </th>
                                        <th scope="col">
                                            <span>姓名</span>
                                        </th>
                                        <th scope="col">
                                            <span>职位</span>
                                        </th>
                                        <th scope="col">
                                            <span>积分</span>
                                        </th>
                                        <th scope="col">
                                            <span>排名</span>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody id="tbody-result">

                                    </tbody>
                                </table>
                            </div>
                            <div class="col-lg-2" id="div-result-public">
                                <input id="btn-result-public" type="button" name="" value="发布">
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="v-pills-plan" role="tabpanel" aria-labelledby="v-pills-plan-tab">
                        <div class="row">
                            <div class="col-lg-9">
                                <table class="table" border="1">
                                    <thead>
                                    <tr>
                                        <th scope="col" colspan=86">云麓谷信息技术中心部门负责人工作计划</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th scope="col">姓名</th>
                                        <td>
                                            <span id="span-plan-name">白云舒</span>
                                        </td>
                                        <th scope="col">部门</th>
                                        <td>
                                            <span id="span-plan-dep">研发部</span>
                                        </td>
                                        <th scope="col">职务</th>
                                        <td>
                                            <span id="span-plan-position">小组长</span>
                                        </td>
                                        <th scope="col">日期</th>
                                        <td>
                                            <span id="span-plan-date">2018-10-18</span>
                                        </td>
                                    </tr>
                                    <tbody>
                                    <tr>
                                        <th colspan="8">
                                            <textarea id="ta-plan-content" rows="10"></textarea>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th scope="row" colspan="8">中南大学网络思想政治工作中心</th>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-lg-2">
                                <input id="btn-plan-submit" type="button" name="" value="提交" onclick="submitPlan()">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<!--<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>-->
<!-- $('#myTab a').on('click', function (e) {
  e.preventDefault()
  $(this).tab('show')
}) -->

<!-- $('#myTab a[href="#profile"]').tab('show') // Select tab by name
$('#myTab li:first-child a').tab('show') // Select first tab
$('#myTab li:last-child a').tab('show') // Select last tab
$('#myTab li:nth-child(3) a').tab('show') // Select third tab -->

<!-- <script>
  $(function () {
    $('#myTab li:last-child a').tab('show')
  })
</script> -->

<!-- <div class="container">
      <div class="tab-content m-4" id="v-pills-tabContent">
        <div class="tab-pane fade show active" id="v-pills-fill" role="tabpanel" aria-labelledby="v-pills-fill-tab">填写考核表</div>
        <div class="tab-pane fade" id="v-pills-view" role="tabpanel" aria-labelledby="v-pills-view-tab">查看结果</div>
        <div class="tab-pane fade" id="v-pills-assess" role="tabpanel" aria-labelledby="v-pills-assess-tab">评估考核</div>
        <div class="tab-pane fade" id="v-pills-public" role="tabpanel" aria-labelledby="v-pills-public-tab">发布结果</div>
        <div class="tab-pane fade" id="v-pills-register" role="tabpanel" aria-labelledby="v-pills-register-tab">人员注册</div>
        <div class="tab-pane fade" id="v-pills-backstage" role="tabpanel" aria-labelledby="v-pills-backstage-tab">后台管理</div>
      </div>
</div> -->