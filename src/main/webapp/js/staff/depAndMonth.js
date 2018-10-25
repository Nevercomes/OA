var selectedDep;
var selectedMonth;

$(document).ready(function () {
    $("#pills-dep-nav").delegate('li', 'click', function () {
        console.log("nav-dep clicked");
        selectedDep = getDepartmentCode($(this).text().replace(/\s+/g,""));
        var nowTab = getNowTabText();
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
        }
    });

    $('#pills-month-nav').delegate('li', 'click', function () {
        console.log("nav-month clicked");
        selectedMonth = getMonthCode($(this).text().replace(/\s+/g,""));
        var nowTab = getNowTabText();
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
    return $('#v-pills-tab').find('.active').text();
}