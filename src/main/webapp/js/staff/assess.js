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
    var requestJson = {};
    requestJson.month = month;

    $('#span-assess-name').text(getGUserName());
    $('#span-assess-dep').text(getGDepartment());
    $('#span-assess-date').text(getCurrentDate());

    $.ajaxSetup({contentType: 'application/json'});
    $.ajax({
        url: 'staff/assess/fill',
        dataType: 'json',
        method: 'get',
        data: requestJson,
        success: function (data) {
            if (data != null) {
                var assessment = data.assessment;
                var name = data.name;
                var dep = data.department;
                assessId = assessment.assessId;
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

function submitAssessment() {

    workRegular = $('#ta-assess-regular').val();
    workOutPlan = $('#ta-assess-out').val();
    workOther = $('#ta-assess-other').val();
    workExpanse = $('#text-assess-expanse').val();
    workPlanSimple = $('#ta-assess-plan').val();
    remark = $('#ta-assess-remark').val();
    assessHeadEva = null;
    assessHeadScore = null;
    assessDirectorEva = null;
    assessDirectorScore = null;

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
        url: 'staff/assess/submit',
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

// userId = getSessionUserId();
// requestJson.userId = userId;

// $('#ta-assess-head-eva').val();
// $('#text-assess-head-score').val();
// $('#ta-assess-dir-eva').val();
// $('#text-assess-dir-score').val();
// $('#ta-assess-remark').val();

// assessHeadEva = $('#assessHeadEva').val();
// assessHeadScore = $('#assessHeadScore').val();
// assessDirectorEva = $('#assessDirectorEva').val();
// assessDirectorScore = $('#assessDirectorScore').val();
// remark = $('#assessRemark').val();

// function saveAssessment() {
//
//     workRegular = $('#assessWorkRegular').val();
//     workOutPlan = $('#assessWorkOutPlan').val();
//     workOther = $('#assessOther').val();
//     workExpanse = $('#assessExpanse').val();
//     workPlanSimple = $('#assessPlanSimple').val();
//
//     var assessmentString = {
//         assessId: assessId, userId: userId, workRegular: workRegular,
//         workOutPlan: workOutPlan, workOther: workOther, workExpanse: workExpanse,
//         workPlanSimple: workPlanSimple, assessHeadEva: assessHeadEva,
//         assessHeadScore: assessHeadScore, assessDirectorEva: assessDirectorEva,
//         assessDirectorScore: assessDirectorScore, remark: remark,
//         month: getLastMonth(), workModifyTime: getCurrentDate(), assessModifyTime: getCurrentDate(), submit: 0
//     };
//     var assessmentJson = $.toJSON(assessmentString);
//     uploadAssessment(assessmentJson);
// }

// assessHeadEva = $('#assessHeadEva').val();
// assessHeadScore = $('#assessHeadScore').val();
// assessDirectorEva = $('#assessDirectorEva').val();
// assessDirectorScore = $('#assessDirectorScore').val();
// remark = $('#assessRemark').val();