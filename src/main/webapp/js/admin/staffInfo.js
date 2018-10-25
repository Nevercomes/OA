function batchRegister() {
    var staffList = getReadResult();
    if(staffList != null) {
        var staffListJson = $.toJSON(staffList); // waiting to confirm
        uploadStaffs(staffListJson);
    }
}

function singleRegister() {
    var userId = $('#text-batch-id').val();
    var name = $('#text-batch-name').val();
    var dep = $('#sel-batch-dep option:selected').text();
    dep = getDepartmentCode(dep);
    var pos = $('#sel-batch-pos option:selected').text();
    pos = getPositionCode(pos);

    var requestJson = {userId:userId, name:name, department:dep, position:pos};
    requestJson = $.toJSON(requestJson);
    uploadStaff(requestJson);
}

function resetStaffPwd() {
    var userId = $('#text-reset-id').val();
    var resetJson = {userId:userId};
    resetJson = $.toJSON(resetJson);
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/account/reset',
        dataType: 'json',
        method: 'post',
        data: resetJson,
        success: function (data) {
            if (data.message === "no authority") {
                alertAuthFade();
            } else if(data.code === 0) {
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

function uploadStaff(staffJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/account/single',
        dataType: 'json',
        method: 'post',
        data: staffJson,
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

function uploadStaffs(staffListJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/account/batch',
        dataType: 'json',
        method: 'post',
        data: staffListJson,
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