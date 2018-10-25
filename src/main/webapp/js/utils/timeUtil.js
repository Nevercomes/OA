function getLastMonth() {
    var mDate = new Date();
    var lastMonth = mDate.getMonth();
    if(lastMonth === 0)
        return 12;
    else
        return lastMonth;
}

function getCurrentMonth() {
    var mDate = new Date();
    return mDate.getMonth() + 1;
}

function getCurrentYear() {
    var mDate = new Date();
    return mDate.getFullYear();
}

function getCurrentDay() {
    var mDate = new Date();
    return mDate.getDay();
}

function getCurrentDate() {
    var mDate = new Date();
    var year = mDate.getFullYear();
    var month = mDate.getMonth() + 1;
    month =(month < 10 ? "0"+month : month);
    var day = mDate.getDate();
    return year + "-" + month + "-" + day;
}

// function getTitleDate() {
//     var mDate = new Date();
//     return mDate.getDate();
// }