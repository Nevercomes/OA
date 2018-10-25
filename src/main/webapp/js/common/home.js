var user = {};
user.name = "";
user.dep = "";
user.pos = "";

var assessStatus;
var assessYear;
var assessMonth;
var startTime;
var endTime;

$(document).ready(function () {
    getUserInfo();
    getAssessInfo();
    if (assessStatus === true) {
        judgeAssessTime();
    }
    initHomePage();
});

function getUserInfo() {

}

function getAssessInfo() {
    $.ajaxSetup({contentType: "application/json"});
    $.ajax({
        url: 'staff/assess/info',
        dataType: 'text',
        method: 'get',
        async: false,
        data: "",
        success: function (data) {
            data = $.parseJSON(data);
            console.log(data.startTime != null);
            if (data.startTime != null) {
                assessStatus = true;
                assessYear = data.year;
                assessMonth = data.month;
                startTime = data.startTime;
                endTime = data.endTime;
            } else {
                assessStatus = false;
            }
        },
        error: function (xhr) {
            // alert('error:' + JSON.stringify(xhr));
            alertFailShow();
        }
    }).done(function (data) {
        console.log('success');
    }).fail(function () {
        console.log('error');
    }).always(function () {
        console.log('complete');
    });
}

function judgeAssessTime() {
    var nowDateStr = getCurrentDate();
    var nowDate = new Date(nowDateStr.replace(/-/g, "\/"));
    var startDate = new Date(startTime.replace(/-/g, "\/"));
    var endDate = new Date(endTime.replace(/-/g, "\/"));
    D(nowDate);
    D(startDate);
    D(endDate);
    nowDate >= startDate && nowDate <= endDate ? assessStatus = true : assessStatus = false;
}

function initHomePage() {
    var month;
    assessStatus ? month=assessMonth : month=getCurrentMonth();
    setNavMonth(month);
    $('#pills-jan-tab').removeClass('active');
    $('#pills-month-nav li a').eq(month-1).attr('class', "nav-link active");
    setNavDep(0);
    onFillTab();
    $('#span-bar-date').text(getCurrentDate());
}

function getAssessStatus() {
    return assessStatus;
}

function getAssessYear() {
    return assessYear;
}

function getAssessMonth() {
    return assessMonth;
}

function getStartTime() {
    return startTime;
}

function getEndTime() {
    return endTime;
}

function getGUserName() {
    return $('#account-menu').text();
}

function getGDepartment() {
    return $('#span-bar-dep').text();
}

function getGPosition() {
    return $('#span-bar-pos').text();
}

// function f()
// {
// 	var a = $("#logo").outerHeight();
// 	var b = a * 4;
// 	document.getElementById("logo").style.width = b+"px";
//
// 	var c = $(".to_fill").width();
// 	var d = c * 0.96 / 10.4;
// 	$("textarea").attr("cols",d);
//
// 	var e = $(".fill_b").width();
// 	var f = $("#filename").width();
// 	var g = (e - f) / 2;
// 	var h = $(".fill_b").height();
// 	var i = $("#filename").height();
// 	var j = (h - i) / 2;
// 	document.getElementById("filename").style.left = g + "px";
// 	document.getElementById("filename").style.top = j + "px";
//
// 	var time = new Date();
// 	var year = time.getFullYear();
// 	var month = time.getMonth() + 1;
// 	document.getElementById("date_year").innerHTML = year;
// 	document.getElementById("date_month").innerHTML = month;
//
// 	setTimeout("f()",100);
// }
//
// $("#left_ico").click(function(){
// 	$(".left").animate({left:"-17%"},500);
// 	$("#left_ico").css({display:"none"});
// 	$("#right_ico").css({display:"inline"});
// 	setTimeout("$('.main').addClass('main_more');",500);
// });
//
// $("#right_ico").click(function(){
// 	$(".left").animate({left:"0"},500);
// 	$("#left_ico").css({display:"inline"});
// 	$("#right_ico").css({display:"none"});
// 	$(".main").removeClass("main_more");
// });
//
// $("#toFill").click(function(){
// 	$("#fill").css({display:"flex"});
// 	$("#see").css({display:"none"});
// 	$("#re").css({display:"none"});
// 	fillAssessment();
// });
//
// $("#toSee").click(function(){
// 	$("#fill").css({display:"none"});
// 	$("#see").css({display:"flex"});
// 	$("#re").css({display:"none"});
// });
//
// $("#toRe").click(function(){
// 	$("#fill").css({display:"none"});
// 	$("#see").css({display:"none"});
// 	$("#re").css({display:"flex"});
// });