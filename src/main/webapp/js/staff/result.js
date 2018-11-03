function viewResult() {

    console.log("viewResult");
    var dep = getSelectedDep();
    var month = getSelectedMonth();
    var requestJson = {department:dep, month:month};
    // requestJson = $.toJSON(requestJson);

    dep === 0 ? $('#span-result-dep').text("") : $('#span-result-dep').text(getDepartmentStr(dep));
    $('#span-result-month').text(getCNMonth(month));

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'staff/result/view',
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
            // alert('error:' + JSON.stringify(xhr));
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

// var data = {"department":"研发部", "staffName":"白云舒", "score":100, "position":"小组长", "rank":1}
// var datas = new Array();
// for(var i=0; i<10; i++) {
// 	datas.push(data);
// }