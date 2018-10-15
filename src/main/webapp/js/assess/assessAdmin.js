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

// 在点进来准备填表的时候
function viewAssessment() {

    var month = getLastMonth();
    var requestString = {userId: userId, month: month};
    var requestJson = $.toJSON(requestString);

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'view',
        dataType: 'json',
        method: 'get',
        data: requestJson,
        success: function (data) {
            if (data != null) {
                assessmentList = JSON.parse(data);
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

function submitEvaluation() {

    workRegular = $('#assessWorkRegular').val();
    workOutPlan = $('#assessWorkOutPlan').val();
    workOther = $('#assessOther').val();
    workExpanse = $('#assessExpanse').val();
    workPlanSimple = $('#assessPlanSimple').val();
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
        month: getLastMonth(), workModifyTime: getCurrentDate(), assessModifyTime: getCurrentDate(), submit: 2
    };
    var assessmentJson = $.toJSON(assessmentString);
    uploadEvaluation(assessmentJson);
}

function uploadEvaluation(assessmentJson) {
    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'evaluate',
        dataType: 'json',
        method: 'post',
        data: assessmentJson,
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

function getAssessmentList() {
    return assessmentList;
}