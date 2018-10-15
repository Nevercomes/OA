function batchRegister() {
    var staffList = getReadResult();
    if(staffList != null) {
        var staffListJson = $.toJSON(staffList); // 或者说它本就是Json
    }
}

function singleRegister() {
    
}

function uploadStaffs(staffListJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'register/batch',
        dataType: 'json',
        method: 'post',
        data: staffListJson,
        success: function (data) {
            var result = JSON.parse(data);
            window.alert(result.result)
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