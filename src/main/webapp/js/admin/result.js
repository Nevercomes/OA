function preViewResult() {

    console.log("viewResult");
    var requestJson = {department:1, month:getLastMonth()};
    // requestJson = $.toJSON(requestJson);

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/result/view',
        dataType: 'json',
        method: 'get',
        data: requestJson,
        success: function (data) {
            if (data != null) {
                for(var i=0; i<data.length; i++)
                    showResult(data[i]);
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

	var dep = 1;
	var month = getLastMonth();
	var requestJson = {department:dep, month:month};

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/result/public',
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