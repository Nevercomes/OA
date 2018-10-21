var planId;

function viewPlan() {

    console.log("viewPlan");
    var requestJson = {};
    requestJson.month = getLastMonth();

    $('#span-plan-name').text(getGUserName());
    $('#span-plan-dep').text(getGDepartment());
    $('#span-plan-position').text(getGPosition());
    $('#span-plan-date').text(getCurrentDate());

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'staff/plan/view',
        dataType: 'json',
        method: 'get',
        data: requestJson,
        success: function (data) {
            if (data != null) {
                var workPlan = data.workPlan;
                var name = data.name;
                var dep = data.department;
                var position = data.position;
                planId = workPlan.planId;
                $('#span-plan-name').text(name);
                $('#span-plan-dep').text(dep);
                $('#span-plan-position').text(position);
                $('#span-plan-date').text(workPlan.modifyTime);
                $('#ta-plan-content').text(workPlan.content);
            } else {
                planId = 0;
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

function submitPlan() {
    var content = $('#ta-plan-content').val();
    var planJson = {planId: planId, content:content, userId: null, month:null,  modifyTime:null };
    planJson = $.toJSON(planJson);
    uploadPlan(planJson);
}

function uploadPlan(planJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'staff/plan/submit',
        dataType: 'json',
        method: 'post',
        data: planJson,
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