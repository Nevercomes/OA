/**
 * window.location.href 一直失效
 */

function logout() {
    $('#btn-logout').click();
}

// function logout() {
//     $.ajax({
//         url: 'account/logout',
//         dataType: 'text',
//         method: 'post',
//         async: false,
//         data: null,
//         success: function (data) {
//             var host = window.location.host;
//             var pro = "/yunlg/oa";
//             var url = host + pro + data;
//             console.log(url);
//             window.location.href = url;
//             window.event.returnValue = false;
//         },
//         error: function (xhr) {
//
//         }
//     }).done(function (data) {
//         console.log('success');
//     }).fail(function () {
//         console.log('error');
//     }).always(function () {
//         console.log('complete');
//     });
//     return false;
// }