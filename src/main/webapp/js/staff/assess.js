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

function fillAssessment() {

    console.log("fillAssessment");
    var month = getSelectedMonth();
    // console.log(month);
    var requestJson = {};
    requestJson.month = month;

    $('#span-assess-month').text(getCNMonth(month));

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
            // 为什么这里不需要parseJSON呢?
            if (data.assessment != null) {
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
                $('#ta-assess-expanse').text(assessment.workExpanse);
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
                assessId = 0;
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

function submitAssessment() {

    workRegular = $('#ta-assess-regular').val();
    workOutPlan = $('#ta-assess-out').val();
    workOther = $('#ta-assess-other').val();
    workExpanse = $('#ta-assess-expanse').val();
    workPlanSimple = $('#ta-assess-plan').val();
    remark = $('#ta-assess-remark').val();
    assessHeadEva = null;
    assessHeadScore = null;
    assessDirectorEva = null;
    assessDirectorScore = null;
    userId = null;

    var assessmentString = {
        assessId: assessId, userId: userId, workRegular: workRegular,
        workOutPlan: workOutPlan, workOther: workOther, workExpanse: workExpanse,
        workPlanSimple: workPlanSimple, assessHeadEva: assessHeadEva,
        assessHeadScore: assessHeadScore, assessDirectorEva: assessDirectorEva,
        assessDirectorScore: assessDirectorScore, remark: remark,
        month: getSelectedMonth(), workModifyTime: null, assessModifyTime: null, submit: 1
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

function getCNMonth(month) {
    switch (month) {
        case 1:
            return "一";
        case 2:
            return "二";
        case 3:
            return "三";
        case 4:
            return "四";
        case 5:
            return "五";
        case 6:
            return "六";
        case 7:
            return "七";
        case 8:
            return "八";
        case 9:
            return "九";
        case 10:
            return "十";
        case 11:
            return "十一";
        case 12:
            return "十二";
    }
}

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