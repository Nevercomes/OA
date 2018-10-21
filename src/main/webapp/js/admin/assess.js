var assessmentList;
var assessId;
var userId;
var workRegular;
var workOutPlan;
var workOther;
var workExpanse;
var workPlanSimple;
var assessHeadEva;
var assessHeadScore;
var assessDirectorEva;
var assessDirectorScore;
var remark;

function evaAssessment() {
	var dep = 1;
    var month = getLastMonth();
    var requestJson = {department:dep, month:month};

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/assess/view',
        dataType: 'json',
        method: 'get',
        data: requestJson,
        success: function (data) {
            if (data != null) {
                setAssessment(0);
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

function setAssessment(index) {
	if(index >= assessmentList.length) {
		return;
	}
	var assessmentWrapper = assessmentList[index];
    var name = assessmentWrapper.name;
    var dep = assessmentWrapper.department;
    var assessment = assessmentWrapper.assessment;
    assessId = assessment.assessId;
    userId = assessment.userId;
    $('#span-assess-name').text(name);
    $('#span-assess-dep').text(getDepartmentStr(dep));
    $('#span-assess-date').text(assessment.workModifyTime);
    $('#ta-assess-regular').text(assessment.workRegular);
    $('#ta-assess-out').text(assessment.workOutPlan);
    $('#ta-assess-other').text(assessment.workOther);
    $('#text-assess-expanse').val(assessment.workExpanse);
    $('#ta-assess-plan').text(assessment.workPlanSimple);
    $('#ta-assess-head-eva').text(assessment.assessHeadEva);
    $('#text-assess-head-score').val(assessment.assessHeadScore);
    $('#ta-assess-dir-eva').text(assessment.assessDirectorEva);
    $('#text-assess-dir-score').val(assessment.assessDirectorScore);
    $('#ta-assess-remark').text(assessment.remark);
    if(assessment.assessHeadScore === -1) {
        $('#text-assess-head-score').val(null);
    }
    if(assessment.assessDirectorScore === -1) {
        $('#text-assess-dir-score').val(null);
    }
}

function submitEva() {
    workRegular = null;
    workOutPlan = null;
    workOther = null;
    workExpanse = null;
    workPlanSimple = null;
    // remark = $('#ta-assess-remark').val();
    assessHeadEva = $('#assessHeadEva').val();
	assessHeadScore = $('#assessHeadScore').val();
	assessDirectorEva = $('#assessDirectorEva').val();
	assessDirectorScore = $('#assessDirectorScore').val();
	remark = $('#assessRemark').val();

    var assessmentString = {
        assessId: assessId, userId: userId, workRegular: workRegular,
        workOutPlan: workOutPlan, workOther: workOther, workExpanse: workExpanse,
        workPlanSimple: workPlanSimple, assessHeadEva: assessHeadEva,
        assessHeadScore: assessHeadScore, assessDirectorEva: assessDirectorEva,
        assessDirectorScore: assessDirectorScore, remark: remark,
        month: getLastMonth(), workModifyTime: null, assessModifyTime: null, submit: 1
    };
    var assessmentJson = $.toJSON(assessmentString);
    uploadEva(assessmentJson);
}

function uploadEva(assessmentJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'admin/assess/submit',
        dataType: 'json',
        method: 'post',
        data: assessmentJson,
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