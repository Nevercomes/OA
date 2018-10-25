function initFillTab() {
    console.log("fill-tab init");
    $('#div-staff-assess-menu').show();
    $('#div-admin-assess-menu').hide();

    $('#span-assess-name').text("");
    $('#span-assess-dep').text("");
    $('#span-assess-date').text("");
    $('#ta-assess-regular').text("");
    $('#ta-assess-out').text("");
    $('#ta-assess-other').text("");
    $('#ta-assess-expanse').text("");
    $('#ta-assess-plan').text("");
    $('#ta-assess-head-eva').text("");
    $('#text-assess-head-score').val("");
    $('#ta-assess-dir-eva').text("");
    $('#text-assess-dir-score').val("");
    $('#ta-assess-remark').text("");

    $('#span-assess-name').removeAttr('disabled');
    $('#span-assess-dep').removeAttr('disabled');
    $('#span-assess-date').removeAttr('disabled');
    $('#ta-assess-regular').removeAttr('disabled');
    $('#ta-assess-out').removeAttr('disabled');
    $('#ta-assess-other').removeAttr('disabled');
    $('#ta-assess-expanse').removeAttr('disabled');
    $('#ta-assess-plan').removeAttr('disabled');
    $('#ta-assess-head-eva').attr('disabled', "true");
    $('#text-assess-head-score').attr('disabled', "true");
    $('#ta-assess-dir-eva').attr('disabled', "true");
    $('#text-assess-dir-score').attr('disabled', "true");
    $('#ta-assess-remark').removeAttr('disabled');

    $('#page-partition').hide();
    $('#pills-dep-nav').hide();
    $('#pills-month-nav').show();
    $('#pills-info-nav').hide();
    $('#pills-back-nav').hide();

    console.log(getAssessStatus());

    if(getAssessStatus() === true) {
        $('#btn-fill-submit').removeAttr('disabled');
        $('#btn-file-mission').removeAttr('disabled');
        $('#btn-file-other').removeAttr('disabled');
    } else {
        $('#btn-fill-submit').attr('disabled', "true");
        $('#btn-file-mission').attr('disabled', "true");
        $('#btn-file-other').attr('disabled', "true");
    }
}

function initPlanTab() {
    console.log("plan-tab init");
    $('#div-plan-staff-menu').show();

    $('#span-plan-name').text("");
    $('#span-plan-dep').text("");
    $('#span-plan-position').text("");
    $('#span-plan-date').text("");
    $('#ta-plan-content').text("");

    $('#page-partition').hide();
    $('#pills-dep-nav').hide();
    $('#pills-month-nav').show();
    $('#pills-info-nav').hide();
    $('#pills-back-nav').hide();

    if(getAssessStatus()) {
        $('#btn-plan-submit').removeAttr('disabled');
    } else {
        $('#btn-plan-submit').attr('disabled', "true");
    }
}

function initViewTab() {
    console.log("view-tab init");
    $('#div-result-public').hide();

    $('#page-partition').hide();
    $('#pills-dep-nav').show();
    $('#pills-month-nav').show();
    $('#pills-info-nav').hide();
    $('#pills-back-nav').hide();
}

function initModifyTab() {
    console.log("modify-tab init");

    $('#page-partition').hide();
    $('#pills-dep-nav').hide();
    $('#pills-month-nav').hide();
    $('#pills-info-nav').hide();
    $('#pills-back-nav').hide();
}

function initEvaTab() {
    console.log("eva-tab init");
    $('#div-staff-assess-menu').hide();
    $('#div-admin-assess-menu').show();

    $('#span-assess-name').text("");
    $('#span-assess-dep').text("");
    $('#span-assess-date').text("");
    $('#ta-assess-regular').text("");
    $('#ta-assess-out').text("");
    $('#ta-assess-other').text("");
    $('#ta-assess-expanse').text("");
    $('#ta-assess-plan').text("");
    $('#ta-assess-head-eva').text("");
    $('#text-assess-head-score').val();
    $('#ta-assess-dir-eva').text();
    $('#text-assess-dir-score').val();
    $('#ta-assess-remark').text();

    $('#span-assess-name').attr('disabled', "true");
    $('#span-assess-dep').attr('disabled', "true");
    $('#span-assess-date').attr('disabled', "true");
    $('#ta-assess-regular').attr('disabled', "true");
    $('#ta-assess-out').attr('disabled', "true");
    $('#ta-assess-other').attr('disabled', "true");
    $('#ta-assess-expanse').attr('disabled', "true");
    $('#ta-assess-plan').attr('disabled', "true");
    var pos = getGPosition();
    if(pos==="指导老师" || pos==="总监") {
        $('#ta-assess-head-eva').attr('disabled', "true");
        $('#text-assess-head-score').attr('disabled', "true");
        $('#ta-assess-dir-eva').removeAttr('disabled');
        $('#text-assess-dir-score').removeAttr('disabled');
    } else if(pos==="部长") {
        $('#ta-assess-head-eva').removeAttr('disabled');
        $('#text-assess-head-score').removeAttr('disabled');
        $('#ta-assess-dir-eva').attr('disabled', "true");
        $('#text-assess-dir-score').attr('disabled', "true");
    }
    $('#ta-assess-remark').removeAttr('disabled');

    $('#page-partition').show();
    $('#pills-dep-nav').show();
    $('#pills-month-nav').show();
    $('#pills-info-nav').hide();
    $('#pills-back-nav').hide();
}

function initPublicTab() {
    console.log("public-tab init");
    $('#div-result-admin-menu').show();

    $('#page-partition').hide();
    $('#pills-dep-nav').show();
    $('#pills-month-nav').show();
    $('#pills-info-nav').hide();
    $('#pills-back-nav').hide();
}

function initViewPlanTab() {
    console.log("viewPlan-tab init");
    $('#span-plan-name').text("");
    $('#span-plan-dep').text("");
    $('#span-plan-position').text("");
    $('#span-plan-date').text("");
    $('#ta-plan-content').text("");

    $('#page-partition').show();
    $('#pills-dep-nav').show();
    $('#pills-month-nav').show();
    $('#pills-info-nav').hide();
    $('#pills-back-nav').hide();
}

function initRegisterTab() {
    console.log("register-tab init");

    $('#page-partition').hide();
    $('#pills-dep-nav').hide();
    $('#pills-month-nav').hide();
    $('#pills-info-nav').show();
    $('#pills-back-nav').hide();
}

function initBackTab() {
    console.log("backstage-tab init");

    $('#page-partition').hide();
    $('#pills-dep-nav').hide();
    $('#pills-month-nav').hide();
    $('#pills-info-nav').hide();
    $('#pills-back-nav').show();
}

function initBackSetTimeTab() {
    console.log("backSetTime-tab init");
    var year = getCurrentYear();
    var month = getLastMonth();
    var day = getCurrentDay();
    if(month === 12) {
        year--;
    }

    $('#text-time-year').val(year);
    $('#text-time-month').val(month);
    year = getCurrentYear();
    month = getCurrentMonth();
    $('#text-time-start-year').val(year);
    $('#text-time-start-month').val(month);
    $('#text-time-start-day').val(day);
    $('#text-time-end-year').val(year);
    $('#text-time-end-month').val(month);
}

// $('#v-pills-fill-tab').on('click', function () {
//
// });
//
// $('#v-pills-plan-tab').on('click', function () {
//
// });
//
// $('#v-pills-view-tab').on('click', function () {
//
// });
//
// $('#v-pills-eva-tab').on('click', function () {
//
// });
//
// $('#v-pills-public-tab').on('click', function () {
//
// });
//
// $('#v-pills-viewPlan-tab').on('click', function () {
//
// });
//
// $('#v-pills-register-tab').on('click', function () {
//
// });
//
// $('#v-pills-backstage-tab').on('click', function () {
//
// });