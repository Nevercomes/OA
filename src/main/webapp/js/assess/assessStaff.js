/***
 * assessName
 * assessDepartment
 * assessDate
 * assessWorkRegular
 * assessWorkOutPlan
 * assessWorkOther
 * assessExpanse
 * assessPlanSimple
 * assessHeadEva
 * assessHeadScore
 * assessDirectorEva
 * assessDirectorScore
 * assessRemark
 */
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

// 在点进来准备填表的时候
function fillAssessment() {

    console.log("fillAssessment");
    var month = getLastMonth();
    var requestJson =  {};
    userId = getSessionUserId();
    requestJson.userId = userId;
    requestJson.month = month;

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'assess/fill',
        dataType: 'json',
        method: 'get',
        data: requestJson,
        success: function (data) {
            if (data != null) {
                var assessment = data;
                assessId = assessment.assessId;
                $('#assessWorkRegular').val(assessment.workRegular);
                $('#assessWorkOutPlan').val(assessment.workOutPlan);
                $('#assessOther').val(assessment.workOther);
                $('#assessExpanse').val(assessment.workExpanse);
                $('#assessPlanSimple').val(assessment.workPlanSimple);
                // $('#assessHeadEva').val(assessment.assessHeadEva);
                // $('#assessHeadScore').val(assessment.assessHeadScore);
                // $('#assessDirectorEva').val(assessment.assessDirectorEva);
                // $('#assessDirectorScore').val(assessment.assessDirectorScore);
                // $('#assessRemark').val(assessment.remark);
            } else {
                assessId = 0;
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

function saveAssessment() {

    workRegular = $('#assessWorkRegular').val();
    workOutPlan = $('#assessWorkOutPlan').val();
    workOther = $('#assessOther').val();
    workExpanse = $('#assessExpanse').val();
    workPlanSimple = $('#assessPlanSimple').val();
    // assessHeadEva = $('#assessHeadEva').val();
    // assessHeadScore = $('#assessHeadScore').val();
    // assessDirectorEva = $('#assessDirectorEva').val();
    // assessDirectorScore = $('#assessDirectorScore').val();
    // remark = $('#assessRemark').val();

    var assessmentString = {
        assessId: assessId, userId: userId, workRegular: workRegular,
        workOutPlan: workOutPlan, workOther: workOther, workExpanse: workExpanse,
        workPlanSimple: workPlanSimple, assessHeadEva: assessHeadEva,
        assessHeadScore: assessHeadScore, assessDirectorEva: assessDirectorEva,
        assessDirectorScore: assessDirectorScore, remark: remark,
        month: getLastMonth(), workModifyTime: getCurrentDate(), assessModifyTime: getCurrentDate(), submit: 0
    };
    var assessmentJson = $.toJSON(assessmentString);
    uploadAssessment(assessmentJson);
}

function submitAssessment() {
    workRegular = $('#assessWorkRegular').val();
    workOutPlan = $('#assessWorkOutPlan').val();
    workOther = $('#assessOther').val();
    workExpanse = $('#assessExpanse').val();
    workPlanSimple = $('#assessPlanSimple').val();
    // assessHeadEva = $('#assessHeadEva').val();
    assessHeadEva = null;
    // assessHeadScore = $('#assessHeadScore').val();
    assessHeadScore = null;
    // assessDirectorEva = $('#assessDirectorEva').val();
    assessDirectorEva = null;
    // assessDirectorScore = $('#assessDirectorScore').val();
    assessDirectorScore = null;
    // remark = $('#assessRemark').val();
    remark = null;

    var assessmentString = {
        assessId: assessId, userId: userId, workRegular: workRegular,
        workOutPlan: workOutPlan, workOther: workOther, workExpanse: workExpanse,
        workPlanSimple: workPlanSimple, assessHeadEva: assessHeadEva,
        assessHeadScore: assessHeadScore, assessDirectorEva: assessDirectorEva,
        assessDirectorScore: assessDirectorScore, remark: remark,
        month: getLastMonth(), workModifyTime: null, assessModifyTime: null, submit: 1
    };
    var assessmentJson = $.toJSON(assessmentString);
    uploadAssessment(assessmentJson);
}

function uploadAssessment(assessmentJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'assess/submit',
        dataType: 'json',
        method: 'post',
        data: assessmentJson,
        success: function (data) {
            var result = data;
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