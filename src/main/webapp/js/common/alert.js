function alertRecordShow() {
    $('#alert-no-record').show();
    alertRecordFade();
}

function alertAuthShow() {
    $('#alert-no-permission').show();
    alertAuthFade();
}

function alertSuccessShow() {
    $('#alert-result-success').show();
    alertSuccessFade();
}

function alertFailShow() {
    $('#alert-result-fail').show();
    alertFailFade();
}

function alertWrongShow() {
    $('#alert-account-wrong').show();
    alertWrongFade();
}

function alertEmptyShow() {
    $('#alert-account-empty').show();
    alertEmptyFade();
}

function alertNotSameShow() {
    $('#alert-account-notSame').show();
    alertNotSameFade();
}

function alertRecordFade() {
    $('#alert-no-record').fadeTo(2500, 0.1, function() {
        $('#alert-no-record').hide();
        $('#alert-no-record').css('opacity', 1);
    });
}

function alertAuthFade() {
    $('#alert-no-permission').fadeTo(2500, 0.1, function() {
        $('#alert-no-permission').hide();
        $('#alert-no-permission').css('opacity', 1);
    });
}

function alertSuccessFade() {
    $('#alert-result-success').fadeTo(2500, 0.1, function() {
        $('#alert-result-success').hide();
        $('#alert-result-success').css('opacity', 1);
    });
}

function alertFailFade() {
    $('#alert-result-fail').fadeTo(2500, 0.1, function() {
        $('#alert-result-fail').hide();
        $('#alert-result-fail').css('opacity', 1);
    });
}

function alertWrongFade() {
    $('#alert-account-wrong').fadeTo(2500, 0.1, function() {
        $('#alert-account-wrong').hide();
        $('#alert-account-wrong').css('opacity', 1);
    });
}

function alertEmptyFade() {
    $('#alert-account-empty').fadeTo(2500, 0.1, function() {
        $('#alert-account-empty').hide();
        $('#alert-account-empty').css('opacity', 1);
    });
}

function alertNotSameFade() {
    $('#alert-account-notSame').fadeTo(2500, 0.1, function() {
        $('#alert-account-notSame').hide();
        $('#alert-account-notSame').css('opacity', 1);
    });
}


function alertHide() {
    $('.alert').hide();
}

// function alertFade(alert) {
//     var timer;
//     var alpha = 100;
//     clearInterval(timer);
//     var speed = 5;
//     timer = setInterval(function () {
//         if (alpha === 0) {
//             clearInterval(timer);
//         } else {
//             alpha -= speed;
//             alert.style.filter = 'alpha(opacity:' + alpha + ')';
//             alert.style.opacity = alpha / 100;
//         }
//     }, 50);
//     alert.hide();
//     alert.style.filter = 'alpha(opacity: 100)';
//     alert.style.opacity = 1;
// }
