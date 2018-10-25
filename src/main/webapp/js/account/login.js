function f() {
    var a = $(".logo").outerHeight();
    var b = $(".board").outerHeight();
    var c = b - a;
    document.getElementById("form").style.height = c + "px";

    var d = $(".username").outerWidth();
    var e = $(".ico").outerWidth(true);
    var f = d - e;
    document.getElementById("text-login-id").style.width = f + "px";
    document.getElementById("pwd-login").style.width = f + "px";

    setTimeout("f()", 100);
}

function guanli()
{
    $(".board").css("height","77%");
    $(".username").css("margin-top","3%");
    $("#guan").css("display","flex");
    $("#b_p").addClass("rec_n");
    $("#b_g").removeClass("rec_n");
    $("#b_g").addClass("rec_y");
}

function putong()
{
    $(".board").css("height","65%");
    $(".username").css("margin-top","3%");
    $("#guan").css("display","none");
    $("#b_g").addClass("rec_n");
    $("#b_p").removeClass("rec_n");
    $("#b_p").addClass("rec_y");
}

function login() {
    var userId = $('#text-login-id').val();
    var password = $('#pwd-login').val();

    var loginJson = {userId:userId, password:password};

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'login/do',
        dataType: 'json',
        method: 'post',
        async: false,
        data: loginJson,
        success: function (data) {
            // show result
        },
        error: function (xhr) {
            // alert('error:' + JSON.stringify(xhr));
        }
    }).done(function (data) {
        console.log('success');
    }).fail(function () {
        console.log('error');
    }).always(function () {
        console.log('complete');
    });
}

// var sessionUserId;
// // var sessionName;
// // var sessionDepartment;
// // var sessionPosition;
// function getSession() {
//     var userId = $("#userId").val();
//     $.ajaxSetup({contentType: 'application/json'});
//     $.ajax({
//         url: 'session',
//         dataType: 'text',
//         method: 'get',
//         data: userId,
//         async: false,
//         success: function (data) {
//             if (data != null) {
//                 var staff = JSON.parse(data);
//                 sessionUserId = staff.userId;
//                 sessionName = staff.name;
//                 sessionDepartment = getDepartmentStr(staff.department);
//                 sessionPosition = staff.position;
//             } else {
//
//             }
//         },
//         error: function (xhr) {
//             // alert('error:' + JSON.stringify(xhr));
//         }
//     }).done(function (data) {
//         console.log('success');
//     }).fail(function () {
//         console.log('error');
//     }).always(function () {
//         console.log('complete');
//     });
// }

