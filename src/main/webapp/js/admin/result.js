function preViewResult() {

    console.log("viewResult");
    var requestJson = {department:getSelectedDep(), month:getSelectedMonth()};
    // requestJson = $.toJSON(requestJson);

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/result/view',
        dataType: 'json',
        method: 'get',
        data: requestJson,
        success: function (data) {
            if (data.length > 0) {
                for(var i=0; i<data.length; i++){
                    showResult(data[i]);
                }
            } else {
                alertRecordShow();
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

function publicResult() {

	var dep = getSelectedDep();
	var month = getSelectedMonth();
	var requestJson = {department:dep, month:month};

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/result/public',
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