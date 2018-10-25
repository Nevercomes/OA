function clickModifyTab() {
    $('#v-pills-modify-tab').click();
}

function modifyPwd() {
    var pwdOld = $('#text-modify-old').val();
    var pwdNew = $('#pwd-modify-new').val();
    var pwdConfirm = $('#pwd-modify-confirm').val();

    if(pwdNew!==pwdConfirm) {
        alertNotSameShow();
    } else if(pwdOld==null || pwdNew==null || pwdConfirm==null) {
        alertEmptyShow();
    } else {
        var requestJson = {oldPassword:pwdOld, newPassword:pwdNew};
        requestJson = $.toJSON(requestJson);
        uploadModifyPwd(requestJson);
    }
}

function uploadModifyPwd(requestJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'account/modify',
        dataType: 'json',
        method: 'post',
        data: requestJson,
        success: function (data) {
            if(data.message === "success"){
                alertSuccessShow();
            } else {
                alertWrongShow();
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