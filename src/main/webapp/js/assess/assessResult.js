/***
 * resultDepartment
 * resultName
 * resultScore
 */

// 在点进来准备填表的时候

function viewResult() {
    var department = 0; // 通过定位的tab来获取
    var month = 9;
    var requestString = {department: department, month: month};
    var requestJson = $.toJSON(requestString);

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'result/edit',
        dataType: 'json',
        method: 'get',
        data: requestJson,
        success: function (data) {
            if (data != null) {
                // 表格呈现
            } else {
                // 显示为空，初始便为空
            }
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

function editReslut() {

    var department = 0; // 通过定位的tab来获取
    var month = 9;
    var requestString = {department: department, month: month};
    var requestJson = $.toJSON(requestString);

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'result/edit',
        dataType: 'json',
        method: 'get',
        data: requestJson,
        success: function (data) {
            if (data != null) {
                // 表格呈现
            } else {

            }
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

function publicResult() {

    var department = 0; // 通过定位的tab来获取
    var month = 9;

    var words = $('#resultWords').val();

    var resultString = {
        department:department,
        month:month,
        words:words
    };

    var resultJson = $.toJSON(resultString);
    uploadEvaluation(resultJson);
}

function uploadEvaluation(resultJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'result/public',
        dataType: 'json',
        method: 'post',
        data: resultJson,
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