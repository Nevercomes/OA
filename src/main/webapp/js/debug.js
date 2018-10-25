$(document).ready(function () {
    $('#test').on('click', function () {
        console.log("hello");
        console.log(window.location.host);
        console.log(window.location.href);
        console.log(window.location.pathname);
    });
});

function D(obj) {
    console.log(typeof(obj) + " " + obj);
}

