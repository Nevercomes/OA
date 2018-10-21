function batchRegister() {
    var staffList = getReadResult();
    if(staffList != null) {
        var staffListJson = $.toJSON(staffList); // 或者说它本就是Json
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
}

function uploadStaffs(staffListJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/register/batch',
        dataType: 'json',
        method: 'post',
        data: staffListJson,
        success: function (data) {
            // var result = JSON.parse(data);
            // window.alert(result.result)
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