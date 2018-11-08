<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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

    <script src="<%=path%>/js/common/home.js"></script>
    <script src="<%=path%>/js/common/enum.js"></script>
    <script src="<%=path%>/js/common/alert.js"></script>

    <script src="<%=path%>/js/utils/timeUtil.js"></script>
    <script src="<%=path%>/js/utils/createTable.js"></script>
    <script src="<%=path%>/js/utils/base64.js"></script>
    <script src="<%=path%>/js/utils/unicodeAndAnsi.js"></script>
    <script src="<%=path%>/js/utils/xls.js"></script>

    <script src="<%=path%>/js/account/login.js"></script>
    <script src="<%=path%>/js/account/logout.js"></script>
    <script src="<%=path%>/js/account/modify.js"></script>

    <script src="<%=path%>/js/staff/assess.js"></script>
    <script src="<%=path%>/js/staff/result.js"></script>
    <script src="<%=path%>/js/staff/plan.js"></script>
    <script src="<%=path%>/js/staff/events.js"></script>
    <script src="<%=path%>/js/staff/init.js"></script>
    <script src="<%=path%>/js/staff/depAndMonth.js"></script>

</head>
<body>

<header class="navbar navbar-expand navbar-light bg-light flex-column flex-md-row bd-navbar">
    <a class="navbar-brand mr-0 mr-md-2" href="#">
        <img id="test" src="<%=path%>/image/logo.png" width="150" height="40">
    </a>
    <div class="navbar-nav-scroll">
        <ul class="navbar-nav bd-navbar-nav flex-row">
            <li class="nav-item"><h4>信息技术中心</h4></li>
        </ul>
    </div>
    <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
        <li class="nav-item active">
            <a class="nav-link" href="#">欢迎 <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <span id="span-bar-dep" class="nav-link" href="#">${sessionScope.department}</span>
        </li>
        <li class="nav-item">
            <span id="span-bar-pos" class="nav-link" href="#">${sessionScope.position}</span>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle mr-md-2" href="#" id="account-menu" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                ${sessionScope.name}
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="account-menu">
                <a class="dropdown-item" href="#" onclick="clickModifyTab()">修改密码</a>
                <div class="dropdown-divider"></div>
                <form action="<%=path%>/account/logout" method="post">
                    <a class="dropdown-item" href="#" onclick="logout()">登出</a>
                    <button id="btn-logout" class="d-none" type="submit"></button>
                </form>
            </div>
        </li>
        <li class="nav-item">
            <span id="span-bar-date" class="nav-link" href="#">2018/10</span>
        </li>
    </ul>
</header>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <div class="col-md-3 col-xl-2 bd-sidebar">
            <ul class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <li class="nav-item mt-2">
                    <a class="nav-link disabled" href="#">For Staff</a>
                </li>
                <li class="nav-item mb-3">
                    <a class="nav-link active" id="v-pills-fill-tab" data-toggle="pill" href="#v-pills-assessment"
                       role="tab"
                       aria-controls="v-pills-assessment" aria-selected="true" onclick="onFillTab()">绩效考核</a>
                </li>
                <li class="nav-item mb-3">
                    <a class="nav-link" id="v-pills-plan-tab" data-toggle="pill" href="#v-pills-plan" role="tab"
                       aria-controls="v-pills-plan" aria-selected="true" onclick="onPlanTab()">工作计划</a>
                </li>
                <li class="nav-item mb-3">
                    <a class="nav-link" id="v-pills-view-tab" data-toggle="pill" href="#v-pills-result" role="tab"
                       aria-controls="v-pills-result" aria-selected="false" onclick="onViewTab()">查看结果</a>
                </li>
                <li class="nav-item mb-3 d-none">
                    <a class="nav-link" id="v-pills-modify-tab" data-toggle="pill" href="#v-pills-modify" role="tab"
                       aria-controls="v-pills-modify" aria-selected="false" onclick="onModifyTab()">修改密码</a>
                </li>
            </ul>
        </div>
        <div class="col-12 col-md-9 col-xl-8 pl-md-5 bd-content ">
            <div class="row">
                <ul class="nav nav-pills mt-3 mb-2 bd-func-bar" id="pills-dep-nav" role="tablist" style="display: none">
                    <li class="nav-item">
                        <a class="nav-link active" id="pills-center-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-home" aria-selected="true">中心</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-rd-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-profile" aria-selected="false">研发</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-networks-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-contact" aria-selected="false">网络</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-art-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-contact" aria-selected="false">美工</a>
                    </li>
                </ul>
            </div>
            <div class="row">
                <ul class="nav nav-pills mt-3 bd-func-bar" id="pills-month-nav" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="pills-jan-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-home" aria-selected="true">Jan</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-feb-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-profile" aria-selected="false">Feb</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-mar-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-contact" aria-selected="false">Mar</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-apr-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-contact" aria-selected="false">Apr</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-may-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-home" aria-selected="true">May</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-june-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-profile" aria-selected="false">June</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-july-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-contact" aria-selected="false">July</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-aug-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-contact" aria-selected="false">Aug</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-sept-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-home" aria-selected="true">Sept</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-oct-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-profile" aria-selected="false">Oct</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-nov-tab" data-toggle="pill" href="#" role="tab"
                           aria-controls="pills-contact" aria-selected="false">Nov</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pills-dec-tab" data-toggle="pill" href="#" role="tab"
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
                                <div class="col-lg-11">
                                    <table class="table bd-table" border="1">
                                        <thead>
                                        <tr>
                                            <th class="bd-table-title" scope="col" colspan="6">云麓谷信息技术中心<span
                                                    id="span-assess-month">九</span>月员工个人绩效考核表
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
                                            <th>常规<br>
                                                工作
                                            </th>
                                            <td colspan="5">
                                                <textarea id="ta-assess-regular" rows="6"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">计划外<br>
                                                工作
                                            </th>
                                            <td colspan="5">
                                                <textarea id="ta-assess-out" rows="6"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">其它<br>
                                                加班
                                            </th>
                                            <td colspan="5">
                                                <textarea id="ta-assess-other" rows="6"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">其它<br>
                                                开支
                                            </th>
                                            <td colspan="5">
                                                <textarea id="ta-assess-expanse" rows="2"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">工作<br>
                                                计划(简)
                                            </th>
                                            <td colspan="5">
                                                <textarea id="ta-assess-plan" rows="6"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row" rowspan="2">部长<br>
                                                评价
                                            </th>
                                            <td colspan="4" rowspan="2">
                                                <textarea id="ta-assess-head-eva" rows="4"></textarea>
                                            </td>
                                            <th>
                                                评分
                                            </th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input class="bd-text-small" id="text-assess-head-score" type="text"
                                                       name="">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row" rowspan="2">总监</br>
                                                评价
                                            </th>
                                            <td colspan="4" rowspan="2">
                                                <textarea id="ta-assess-dir-eva" rows="4"></textarea>
                                            </td>
                                            <th>
                                                评分
                                            </th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input class="bd-text-small" id="text-assess-dir-score" type="text"
                                                       name="">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">备注</th>
                                            <td colspan="5">
                                                <textarea id="ta-assess-remark" rows="4"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row" colspan="6" style="text-align: right;">中南大学网络思想政治工作中心</th>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div id="div-staff-assess-menu" class="col-lg-1 table-right-menu">
                                    <input id="btn-fill-submit" class="btn btn-primary mb-3" type="button" name=""
                                           value="提交" onclick="submitAssessment()">
                                    <input id="btn-file-mission" class="btn btn-primary mb-3" type="button" name=""
                                           value="上传任务文件">
                                    <input id="btn-file-other" class="btn btn-primary mb-3" type="button" name=""
                                           value="上传其它文件">
                                </div>
                            </div>

                        </div>
                        <div class="tab-pane fade" id="v-pills-result" role="tabpanel"
                             aria-labelledby="v-pills-result-tab">
                            <div class="row">
                                <div class="col-lg-11">
                                    <table class="table bd-table" border="1">
                                        <thead>
                                        <tr>
                                            <th class="bd-table-title" scope="col" colspan="5">云麓谷信息技术中心
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
                            </div>
                        </div>
                        <div class="tab-pane fade" id="v-pills-plan" role="tabpanel" aria-labelledby="v-pills-plan-tab">
                            <div class="row">
                                <div class="col-lg-11">
                                    <table class="table bd-table" border="1">
                                        <thead>
                                        <tr>
                                            <th class="bd-table-title" scope="col" colspan="8">云麓谷信息技术中心部门负责人工作计划</th>
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
                                                <textarea id="ta-plan-content" rows="30"></textarea>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th scope="row" colspan="8" style="text-align: right;">中南大学网络思想政治工作中心</th>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div id="div-plan-staff-menu" class="col-lg-1 table-right-menu">
                                    <input id="btn-plan-submit" class="btn btn-primary" type="button" name=""
                                           value="提交" onclick="submitPlan()">
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="v-pills-modify" role="tabpanel"
                             aria-labelledby="v-pills-modify-tab">
                            <h5>修改密码</h5>
                            <form>
                                <div class="form-group row ml-1">
                                    <label for="text-modify-old" class="col-form-label mr-3">原始密码</label>
                                    <div class="">
                                        <input type="text" class="form-control" id="text-modify-old">
                                    </div>
                                </div>
                                <div class="form-group row ml-1">
                                    <label for="pwd-modify-new" class="col-form-label mr-3">新置密码</label>
                                    <div class="">
                                        <input type="password" class="form-control" id="pwd-modify-new">
                                    </div>
                                </div>
                                <div class="form-group row ml-1">
                                    <label for="pwd-modify-confirm" class="col-form-label mr-3">确认密码</label>
                                    <div class="">
                                        <input type="password" class="form-control" id="pwd-modify-confirm">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input class="btn btn-primary" type="button" value="确认修改"
                                           onclick="modifyPwd()">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="alert-no-record" class="alert alert-warning fade show bd-alert" role="alert">
    <strong>Sorry, no record temporarily</strong>
</div>
<div id="alert-no-permission" class="alert alert-danger fade show bd-alert" role="alert">
    <strong>Sorry, you have no permission</strong>
</div>
<div id="alert-result-success" class="alert alert-success fade show bd-alert" role="alert">
    <strong>Congratulations, operated successfully</strong>
</div>
<div id="alert-result-fail" class="alert alert-danger fade show bd-alert" role="alert">
    <strong>Sorry, that operation failed</strong>
</div>
<div id="alert-account-wrong" class="alert alert-danger fade show bd-alert" role="alert">
    <strong>Sorry, your password is wrong</strong>
</div>
<div id="alert-account-empty" class="alert alert-warning fade show bd-alert" role="alert">
    <strong>Please fulfill the input text</strong>
</div>
<div id="alert-account-notSame" class="alert alert-warning fade show bd-alert" role="alert">
    <strong>please check your confirm password</strong>
</div>
</body>
</html>