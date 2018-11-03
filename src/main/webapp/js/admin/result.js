function preViewResult() {

    console.log("viewResult");
    var dep = getSelectedDep();
    var month = getSelectedMonth();
    var requestJson = {department:dep, month:month};
    // requestJson = $.toJSON(requestJson);

    dep === 0 ? $('#span-result-dep').text("") : $('#span-result-dep').text(getDepartmentStr(dep));
    $('#span-result-month').text(getCNMonth(month));

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/result/view',
        dataType: 'json',
        method: 'get',
        data: requestJson,
        success: function (data) {
            if (data.length > 0 && data[0].length > 0 || data.length === 3) {
                console.log(data);
                showResult(data);
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