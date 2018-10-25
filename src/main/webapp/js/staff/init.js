function initFillTab() {
    console.log("fill-tab init");

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

    $('#pills-dep-nav').hide();
    $('#pills-month-nav').show();

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

    $('#span-plan-name').text("");
    $('#span-plan-dep').text("");
    $('#span-plan-position').text("");
    $('#span-plan-date').text("");
    $('#ta-plan-content').text("");

    $('#page-partition').hide();
    $('#pills-dep-nav').hide();
    $('#pills-month-nav').show();
    $('#pills-back-nav').hide();

    if(getAssessStatus()) {
        $('#btn-plan-submit').removeAttr('disabled');
    } else {
        $('#btn-plan-submit').attr('disabled', "true");
    }
}

function initViewTab() {
    console.log("view-tab init");
    $('#div-result-admin-menu').hide();

    $('#pills-dep-nav').show();
    $('#pills-month-nav').show();
}

function initModifyTab() {
    console.log("modify-tab init");

    $('#pills-dep-nav').hide();
    $('#pills-month-nav').hide();
}




