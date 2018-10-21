$('#v-pills-fill-tab').on('click', function() {
    console.log('clicked');
    $('#div--fill-submit').show();
    $('#div-eva-submit').hide();
});

$('#v-pills-eva-tab').on('click', function() {
    console.log('clicked');
    $('#div-fill-submit').hide()
    $('#div-eva-submit').show();
});

$('#v-pills-view-tab').on('click', function () {
    console.log('clicked');
    $("#btn-result-public").hide();
});

$('#v-pills-public-tab').on('click', function () {
    console.log('clicked');
    $("#btn-result-public").show();
});

$('#btn-result-public').on('click', function () {
    console.log('clicked');
});

$('#v-pills-plan-tab').on('click', function (){
    console.log('clicked');
    $('#btn-plan-submit').show();
});

$('#v-pills-viewPlan-tab').on('click', function (){
    console.log('clicked');
    $('#btn-plan-submit').hide();
});