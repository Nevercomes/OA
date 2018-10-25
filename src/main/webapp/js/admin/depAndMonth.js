var selectedDep;
var selectedMonth;

$(document).ready(function () {
    $("#pills-dep-nav").delegate('li', 'click', function () {
        console.log("nav-dep clicked");
        // var idx = $('li').index($(this));
        selectedDep = getDepartmentCode($(this).text().replace(/\s+/g,""));
        // console.log(idx);
        var nowTab = getNowTabText();
        // console.log(nowTab);
        switch (nowTab) {
            case "绩效考核":
                onFillTab();
                break;
            case "工作计划":
                onPlanTab();
                break;
            case "查看结果":
                onViewTab();
                break;
            case "评估考核":
                onEvaTab();
                break;
            case "发布结果":
                onPublicTab();
                break;
            case "审阅计划":
                onViewPlanTab();
                break;
            case "员工信息":
                onRegisterTab();
                break;
            case "后台管理":
                onBackTab();
                break;
        }
    });

    $('#pills-month-nav').delegate('li', 'click', function () {
        console.log("nav-month clicked");
        // var idx = $('li').index($(this));
        // console.log($(this).text().replace(/\s+/g,""));
        selectedMonth = getMonthCode($(this).text().replace(/\s+/g,""));
        // console.log(selectedMonth);
        // console.log(idx);
        // console.log();
        var nowTab = getNowTabText();
        // console.log(nowTab);
        switch (nowTab) {
            case "绩效考核":
                onFillTab();
                break;
            case "工作计划":
                onPlanTab();
                break;
            case "查看结果":
                onViewTab();
                break;
            case "评估考核":
                onEvaTab();
                break;
            case "发布结果":
                onPublicTab();
                break;
            case "审阅计划":
                onViewPlanTab();
                break;
            case "员工信息":
                onRegisterTab();
                break;
            case "后台管理":
                onBackTab();
                break;
        }
    });
});

function setNavDep(dep) {
    selectedDep = dep;
}

function setNavMonth(month) {
    selectedMonth = month;
}

function getSelectedDep() {
    return selectedDep;
}

function getSelectedMonth() {
    return selectedMonth;
}

function getNowTabText() {
    // console.log($('#v-pills-tab li').text());
    // console.log($('#v-pills-assessment').text());

    return $('#v-pills-tab').find('.active').text();
}