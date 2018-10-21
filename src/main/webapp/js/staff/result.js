
function viewResult() {

    console.log("viewResult");
    var requestJson = {department:1, month:getLastMonth()};
    // requestJson = $.toJSON(requestJson);

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'staff/result/view',
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

// var data = {"department":"研发部", "staffName":"白云舒", "score":100, "position":"小组长", "rank":1}
// var datas = new Array();
// for(var i=0; i<10; i++) {
// 	datas.push(data);
// }