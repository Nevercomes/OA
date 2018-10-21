var planList;

function evaPlan() {
    var dep = 1;
    var month = getLastMonth();
    var requestJson = {department:dep, month:month};

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/plan/view',
        dataType: 'json',
        method: 'get',
        data: requestJson,
        success: function (data) {
            if (data != null) {
                planList = data;
                setPlan(0);
            } else {
                // planId = 0;
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

function setPlan(index) {
    if(index >= planList.length)
        return;
    var planWrapper = planList[index];
    var workPlan = planWrapper.workPlan;
    var name = planWrapper.name;
    var dep = planWrapper.department;
    var position = planWrapper.position;
    planId = workPlan.planId;
    $('#span-plan-name').text(name);
    $('#span-plan-dep').text(dep);
    $('#span-plan-position').text(position);
    $('#span-plan-date').text(workPlan.modifyTime);
    $('#ta-plan-content').text(workPlan.content);
}