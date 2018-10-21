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
    var requestJson = {userId:userId, name:name, password:password, department:dep, position:pos};
    requestJson = $.toJSON(requestJson);

    submitRegister(requestJson);
}

function viewStaffInfo() {

}

function submitRegister(requestJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/register',
        dataType: 'json',
        method: 'post',
        data: requestJson,
        success: function (data) {
            // var result = data;
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