function adminRegister() {
    var userId = $('#text-admin-reg-id').val();
    var name = $('#text-admin-reg-name').val();
    var password = $('#pwd-admin-reg-pwd').val();
    password = decode64(password);
    var dep = $('#sel-admin-reg-dep option:selected').text();
    dep = getDepartmentCode(dep);
    console.log(dep);
    var pos = $('#sel-admin-reg-pos option:selected').text();
    pos = getPositionCode(pos);
    console.log(pos);
    var requestJson = {userId: userId, name: name, password: password, department: dep, position: pos};
    requestJson = $.toJSON(requestJson);

    submitRegister(requestJson);
}

function submitRegister(requestJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/register',
        dataType: 'json',
        method: 'post',
        data: requestJson,
        success: function (data) {
            if (data.code === 0) {
                alertFailShow();
            } else {
                alertSuccessShow();
            }
        },
        error: function (xhr) {
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

function setAssessTime() {
    var year = $('#text-time-year').val();
    var month = $('#text-time-month').val();
    var startYear = $('#text-time-start-year').val();
    var startMonth = $('#text-time-start-month').val();
    var startDay = $('#text-time-start-day').val();
    var endYear = $('#text-time-end-year').val();
    var endMonth = $('#text-time-end-month').val();
    var endDay = $('#text-time-end-day').val();

    var startTime = startYear + "-" + startMonth + "-" + startDay;
    var endTime = endYear + "-" + endMonth + "-" + endDay;

    var assessTime = {
        assessRecordId: 0,
        year: year,
        month: month,
        startTime: startTime,
        endTime: endTime,
        modifyTime: null
    };

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/assess/setTime',
        dataType: 'json',
        method: 'post',
        data: assessTime,
        success: function (data) {
            if (data.code === 0) {
                alertFailShow();
            } else {
                alertSuccessShow();
            }
        },
        error: function (xhr) {
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

// function getAssessTime() {
//
// }